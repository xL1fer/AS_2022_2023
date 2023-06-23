/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HCP.BedRoom;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import HCP.Log.ILog_Customer;
import HCP.Main.Main;
import HCP.Log.ILog_ControlCentreProxy;

/**
 *
 * Bed Room monitor.
 */
public class MBedRoom implements IBedRoom {
    /**
     * Reentrantlock synchronization object.
     */
    private final ReentrantLock rl;
    /**
     * Wait checkout button synchronization condition.
     */
    private final Condition cWaitCheckOutButton;
    /**
     * Wait bathroom synchronization condition.
     */
    private final Condition cWaitBathroom;
    /**
     * Wait resume button synchronization condition.
     */
    private final Condition cWaitResumeButton;
    /**
     * CCP message log monitor.
     */
    private final ILog_ControlCentreProxy mLogCCP;
    /**
     * Customer message log monitor.
     */
    private final ILog_Customer mLogCustomer;
    /**
     * Hostel bedrooms array.
     */
    private final int[][][] bedRooms;
    /**
     * Hostel bathrooms array.
     */
    private final int[][] bathroomStatus;
    /**
     * Check-out started flag.
     */
    private boolean checkOutStarted;
    /**
     * Check-in transition flag.
     */
    private boolean checkInTransition;
    /**
     * Floor checkout number.
     */
    private int floorCheckOut;
    /**
     * Customer's bathroom time.
     */
    private int timeBathroom;
    /**
     * Current customers in all floors.
     */
    private int currentCustomers;
    /**
     * Simulation suspended status.
     */
    private boolean isSuspended;
    /**
     * Simulation mode status.
     */
    private boolean isAuto;
    
    /**
     * Bed Room class constructor.
     * 
     * @param mLogCCP CCP message log monitor
     * @param mLogCustomer cusotmer message log monitor
     */
    private MBedRoom(ILog_ControlCentreProxy mLogCCP, ILog_Customer mLogCustomer) {
        this.rl = new ReentrantLock();
        this.cWaitCheckOutButton = rl.newCondition();
        this.cWaitBathroom = rl.newCondition();
        this.cWaitResumeButton = this.rl.newCondition();
        this.mLogCCP = mLogCCP;
        this.mLogCustomer = mLogCustomer;
        this.bedRooms = new int[3][3][3];
        this.floorCheckOut = 0;
        this.checkOutStarted = true;
        this.checkInTransition = true;
        this.timeBathroom = 100;
        this.bathroomStatus = new int[3][3];
        this.currentCustomers = 0;
        this.isSuspended = false;
        this.isAuto = true;
    }

    /**
     * Create instance of bedroom monitor.
     * 
     * @param mLogCCP CCP message log monitor
     * @param mLogCustomer cusotmer message log monitor
     * @return bed room monitor instance
     */
    public static IBedRoom getInstance(ILog_ControlCentreProxy mLogCCP, ILog_Customer mLogCustomer) {
        return new MBedRoom(mLogCCP, mLogCustomer);
    }
    
    /**
     * Update floor GUI.
     * 
     * @param currentFloor floor to be updated
     */
    public void updateFloorGUI(int currentFloor) {
        javax.swing.JList<String> floor;
        if (currentFloor == 1)
            floor = Main.floor1;
        else if (currentFloor == 2)
            floor = Main.floor2;
        else
            floor = Main.floor3;
        
        String[] strings = new String[9];
        for (int i = 0; i < 9; i++) {
            if (this.bedRooms[currentFloor - 1][i / 3][i % 3] < 10) {
                strings[i] = "00";
                strings[i] += Integer.toString(this.bedRooms[currentFloor - 1][i / 3][i % 3]);
            }
            else
                strings[i] = "0" + Integer.toString(this.bedRooms[currentFloor - 1][i / 3][i % 3]);

        }
        floor.setModel(new javax.swing.AbstractListModel<String>() {
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
    }
    
    /**
     * Update bathroom GUI.
     * 
     * @param currentFloor floor to be updated
     */
    public void updateBathroomsGUI(int currentFloor) {
        javax.swing.JList<String> bathrooms;
        if (currentFloor == 1)
            bathrooms = Main.bathrooms1;
        else if (currentFloor == 2)
            bathrooms = Main.bathrooms2;
        else
            bathrooms = Main.bathrooms3;
        
        String[] strings = new String[3];
        for (int i = 0; i < 3; i++) {
            if (this.bathroomStatus[currentFloor - 1][i] < 10) {
                strings[i] = "0";
                strings[i] += Integer.toString(this.bathroomStatus[currentFloor - 1][i]);
            }
            else
                strings[i] = Integer.toString(this.bathroomStatus[currentFloor - 1][i]);

        }
        bathrooms.setModel(new javax.swing.AbstractListModel<String>() {
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
    }
    
    @Override
    public void setTimeBathroom(int timeBathroom) {
        this.timeBathroom = timeBathroom;
    }
    
    @Override
    public void suspendSimulation() {
        if (!this.isSuspended && this.checkOutStarted && !this.checkInTransition) {
            this.mLogCCP.ccp_logSuspend(false);
        }
        this.isSuspended = true;
    }
    
    @Override
    public void resumeSimulation(boolean resume) {
        try {
            this.rl.lock();
            
            // log step simulation if simulation is in manual and the command is step (resume = false)
            if (!this.isAuto && !resume && this.checkOutStarted && !this.checkInTransition) {
                this.mLogCCP.ccp_logStep(false);
                this.cWaitResumeButton.signalAll();
            }
            
            if (resume) {
                // log resume simulation if simulation was suspended
                if (this.isSuspended && this.checkOutStarted && !this.checkInTransition) {
                    this.mLogCCP.ccp_logResume(false);
                }
                
                this.isSuspended = false;
                if (this.isAuto)
                    this.cWaitResumeButton.signalAll();
            }
        } finally {
            this.rl.unlock();
        }
    }
    
    @Override
    public void manualSimulation() {
        if (this.isAuto && this.checkOutStarted && !this.checkInTransition) {
            this.mLogCCP.ccp_logManual(false);
        }
        this.isAuto = false;
    }
    
    @Override
    public void autoSimulation() {
        try {
            this.rl.lock();
            
            if (!this.isAuto && this.checkOutStarted && !this.checkInTransition) {
                this.mLogCCP.ccp_logAuto(false);
            }
            
            this.isAuto = true;
            this.cWaitResumeButton.signalAll();
        } finally {
            this.rl.unlock();
        }
    }
    
    @Override
    public void startCheckOut() {
        try {
            this.rl.lock();
            
            // only allow one checkout at a time
            if (!this.checkOutStarted) {
                System.out.println("Signaling checkout");
                this.floorCheckOut++;
                this.mLogCCP.ccp_logCheckOut(this.floorCheckOut);
                this.cWaitCheckOutButton.signalAll();
                this.checkOutStarted = true;
            }
        } finally {
            this.rl.unlock();
        }
    }
    
    @Override
    public void goToBed(int customerId, int bedOffset) {
        int floorNumber = bedOffset / 9;
        int bedRoomNumber = (bedOffset % 9) / 3;
        int bedNumber = ((bedOffset % 9) % 3);
            
        try {
            this.rl.lock();
            
            System.out.printf("> Customer %d is going to bed (offset = %d) (bed = %d) (room = %d) (floor = %d)\n", customerId, bedOffset, bedNumber, bedRoomNumber, floorNumber);
            
            this.currentCustomers++;
            this.bedRooms[floorNumber][bedRoomNumber][bedNumber] = customerId;
            
            updateFloorGUI(floorNumber + 1);
            
            while (this.floorCheckOut != floorNumber + 1) {
                try {
                    this.cWaitCheckOutButton.await();
                } catch (InterruptedException ex){}
            }
        } finally {
            this.rl.unlock();
        }
    }

    @Override
    public void waitBathroom(int customerId, int bedOffset) {
        int floorNumber = bedOffset / 9;
        int bedRoomNumber = (bedOffset % 9) / 3;
        int bedNumber = ((bedOffset % 9) % 3);
            
        try {
            this.rl.lock();
            
            while (this.isSuspended || !this.isAuto) {
                try {
                    this.cWaitResumeButton.await();
                } catch (InterruptedException ex){}
                if (!this.isSuspended && !this.isAuto)
                    break;
            }
            
            System.out.printf("> Customer %d waiting bathroom\n", customerId);
            
            while (this.bathroomStatus[floorNumber][bedRoomNumber] != 0) {
                try {
                    this.cWaitBathroom.await();
                } catch (InterruptedException ex){}
            }
            
            this.bedRooms[floorNumber][bedRoomNumber][bedNumber] = 0;
            updateFloorGUI(floorNumber + 1);
            
            this.bathroomStatus[floorNumber][bedRoomNumber] = customerId;
            updateBathroomsGUI(floorNumber + 1);
            
        } finally {
            this.rl.unlock();
        }
    }

    @Override
    public void useBathroom(int customerId, int bedOffset) {
        int floorNumber = bedOffset / 9;
        int bedRoomNumber = (bedOffset % 9) / 3;
        int bedNumber = ((bedOffset % 9) % 3);

        try {
            System.out.printf("> Customer %d is using bathroom (bed = %d) (room = %d) (floor = %d)\n", customerId, floorNumber, bedRoomNumber, bedNumber);
            this.mLogCustomer.customer_logBathroom(customerId, bedRoomNumber);
            Thread.sleep(this.timeBathroom);
        } catch (InterruptedException ex) {}
        
        try {
            this.rl.lock();
            
            while (this.isSuspended || !this.isAuto) {
                try {
                    this.cWaitResumeButton.await();
                } catch (InterruptedException ex){}
                if (!this.isSuspended && !this.isAuto)
                    break;
            }
            
            this.bathroomStatus[floorNumber][bedRoomNumber] = 0;
            updateBathroomsGUI(floorNumber + 1);
            
            this.currentCustomers--;
            // last customer resets floor checkout
            if (this.currentCustomers == 0)
                this.floorCheckOut = 0;
                
            this.cWaitBathroom.signalAll();
        } finally {
            this.rl.unlock();
        }
    }
    
    @Override
    public boolean resetCheckOut(int porterTotalCustomers) {
        try {
            this.rl.lock();
            
            // all customers left
            if (porterTotalCustomers == 0) {
                this.checkInTransition = true;
                return false;
            }
            
            System.out.printf("> Porter is preparing next check-out (total customers = %d)\n", porterTotalCustomers);
            this.checkOutStarted = false;
            this.checkInTransition = false;
        } finally {
            this.rl.unlock();
        }
        
        return true;
    }
}
