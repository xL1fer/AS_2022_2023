/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HCP.Comms;

import java.net.Socket;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import Utils.Message;
import HCP.ActiveEntity.*;
import HCP.Log.*;
import HCP.Outside.*;
import HCP.CheckIn.*;
import HCP.BedRoom.*;
import HCP.MealRoom.*;
import HCP.Leaving.*;

/**
 *
 * Communication handle thread class.
 */
public class TControlCentreProxy implements Runnable {
    // socket connection attributes
    /**
     * Server TCP socket connection.
     */
    private final Socket socket;
    /**
     * Server TCP socket output.
     */
    private final ObjectOutputStream out;
    /**
     * Client TCP socket input.
     */
    private final ObjectInputStream in;
    
    // monitor attributes
    /**
     * CCP message log monitor.
     */
    private final ILog_ControlCentreProxy mLogCCP;
    /**
     * Customer message log monitor.
     */
    private final ILog_Customer mLogCustomer;
    /**
     * Porter message log monitor.
     */
    private final ILog_Porter mLogPorter;
    /**
     * Receptionist message log monitor.
     */
    private final ILog_Receptionist mLogReceptionist;
    /**
     * Waiter message log monitor.
     */
    private final ILog_Waiter mLogWaiter;
    /**
     * Outside monitor.
     */
    private IOutside mOutside;
    /**
     * Check-in monitor.
     */
    private ICheckIn mCheckIn;
    /**
     * Bed room monitor.
     */
    private IBedRoom mBedRoom;
    /**
     * Meal room monitor.
     */
    private IMealRoom mMealRoom;
    /**
     * Leaving monitor.
     */
    private ILeaving mLeaving;
    
    /**
     * Control centre proxy class constructor.
     * 
     * @param socket server TCP socket connection
     * @param mLogMessage general message log monitor
     * @throws IOException
     */
    private TControlCentreProxy(Socket socket, ILog mLogMessage) throws IOException {
        this.socket = socket;
        this.out = new ObjectOutputStream(socket.getOutputStream());
        this.in = new ObjectInputStream(socket.getInputStream());
        
        this.mLogCCP = (ILog_ControlCentreProxy)mLogMessage;
        this.mLogCustomer = (ILog_Customer)mLogMessage;
        this.mLogPorter = (ILog_Porter)mLogMessage;
        this.mLogReceptionist = (ILog_Receptionist)mLogMessage;
        this.mLogWaiter = (ILog_Waiter)mLogMessage;
    }
    
    /**
     * Create instance of control centre proxy object.
     * 
     * @param socket server TCP socket connection
     * @param mLogMessage general message log monitor
     * @return control centre proxy object instance
     * @throws IOException exception
     */
    public static Runnable getInstance(Socket socket, ILog mLogMessage) throws IOException {
        return new TControlCentreProxy(socket, mLogMessage);
    }
    
    /**
     * Initialize all thread instances.
     */
    public void initializeInstances() {
        // initialize monitors
        this.mOutside = MOutside.getInstance(mLogCCP);
        this.mCheckIn = MCheckIn.getInstance(mLogCustomer, mLogPorter, mLogReceptionist);
        this.mBedRoom = MBedRoom.getInstance(mLogCCP, mLogCustomer);
        this.mMealRoom = MMealRoom.getInstance(mLogCustomer, mLogWaiter);
        this.mLeaving = MLeaving.getInstance(mLogCustomer, mLogPorter);

        ArrayList<Thread> threads = new ArrayList<Thread>();

        // initialize customers
        for (int i = 0; i < 27; i++) {
            Thread customer = new Thread(TCustomer.getInstance(i + 1, (IOutside_Customer)mOutside, (ICheckIn_Customer)mCheckIn,
                    (IBedRoom_Customer)mBedRoom, (IMealRoom_Customer)mMealRoom, (ILeaving_Customer)mLeaving));
            threads.add(customer);
            customer.start();
        }

        // initialize porter
        Thread porter = new Thread(TPorter.getInstance((IOutside_Porter)mOutside, (ICheckIn_Porter)mCheckIn, (IBedRoom_Porter)mBedRoom, (ILeaving_Porter)mLeaving));
        threads.add(porter);
        porter.start();

        // initialize receptionists
        for (int i = 0; i < 3; i++) {
            Thread receptionist = new Thread(TReceptionist.getInstance(i + 1, (ICheckIn_Receptionist)mCheckIn));
            threads.add(receptionist);
            receptionist.start();
        }

        // initialize waiter
        Thread waiter = new Thread(TWaiter.getInstance((ICheckIn_Waiter)mCheckIn, (IMealRoom_Waiter)mMealRoom));
        threads.add(waiter);
        waiter.start();
        
        //System.out.println("Instances initialized");
    }
    
    @Override
    public void run() {
        while (true) {
            try {
                try (in; out; socket) {
                    System.out.println("Established connection with the Control Center");
                    
                    // initialize monitors and threads
                    initializeInstances();
                    mLogCCP.ccp_logOpen();
                    while (true) {
                        Message msgIn = (Message) in.readObject();
                        msgIn.printMessage();
                        out.writeObject(Message.getInstance(msgIn));
                        
                        // handle control centre commands
                        if (msgIn.getAction().contains("Start")) {
                            // initialize monitors variables
                            mCheckIn.setTimeCheckIn(msgIn.getTimeCheckIn());
                            mBedRoom.setTimeBathroom(msgIn.getTimeBathroom());
                            mMealRoom.setTimeBreakfast(msgIn.getTimeBreakfast());
                            
                            // start next simulation
                            mOutside.nextSimulation(msgIn.getCustomers());
                        }
                        else if (msgIn.getAction().contains("Check-in")) {
                            mOutside.startCheckIn();
                        }
                        else if (msgIn.getAction().contains("Check-out")) {
                            mBedRoom.startCheckOut();
                        }
                        else if (msgIn.getAction().contains("Suspend")) {
                            mOutside.suspendSimulation();
                            mCheckIn.suspendSimulation();
                            mBedRoom.suspendSimulation();
                            mMealRoom.suspendSimulation();
                            mLeaving.suspendSimulation();
                            System.out.println("> Suspending simulation");
                        }
                        else if (msgIn.getAction().contains("Resume")) {
                            mOutside.resumeSimulation(true);
                            mCheckIn.resumeSimulation(true);
                            mBedRoom.resumeSimulation(true);
                            mMealRoom.resumeSimulation(true);
                            mLeaving.resumeSimulation(true);
                            System.out.println("> Resuming simulation");
                        }
                        else if (msgIn.getAction().contains("Manual")) {
                            mOutside.manualSimulation();
                            mCheckIn.manualSimulation();
                            mBedRoom.manualSimulation();
                            mMealRoom.manualSimulation();
                            mLeaving.manualSimulation();
                            System.out.println("> Switching simulation to manual");
                        }
                        else if (msgIn.getAction().contains("Auto")) {
                            mOutside.autoSimulation();
                            mCheckIn.autoSimulation();
                            mBedRoom.autoSimulation();
                            mMealRoom.autoSimulation();
                            mLeaving.autoSimulation();
                            System.out.println("> Switching simulation to auto");
                        }
                        else if (msgIn.getAction().contains("Step")) {
                            mOutside.resumeSimulation(false);
                            mCheckIn.resumeSimulation(false);
                            mBedRoom.resumeSimulation(false);
                            mMealRoom.resumeSimulation(false);
                            mLeaving.resumeSimulation(false);
                            System.out.println("> Step simulation");
                        }
                        else if (msgIn.getAction().contains("End")) {
                            mLogCCP.ccp_logClose();
                            
                            System.out.println("Simulation ended");
                            System.exit(0);
                        }
                    }
                }
            } catch (IOException ex) {
                System.err.println("IOException: " + ex.getMessage());
                System.exit(1);
            } catch (ClassNotFoundException ex) {
                System.err.println("ClassNotFoundException: " + ex.getMessage());
                System.exit(1);
            }
        }
    }
}
