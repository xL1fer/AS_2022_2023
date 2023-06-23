/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HCP.ActiveEntity;

import HCP.Outside.IOutside_Porter;
import HCP.CheckIn.ICheckIn_Porter;
import HCP.BedRoom.IBedRoom_Porter;
import HCP.Leaving.ILeaving_Porter;

/**
 *
 * Porter thread class behaviour.
 */
public class TPorter implements Runnable {
    /**
    * Outside monitor.
    */
    private final IOutside_Porter mOutside;
    /**
    * CheckIn monitor.
    */
    private final ICheckIn_Porter mCheckIn;
    /**
    * BedRoom monitor.
    */
    private final IBedRoom_Porter mBedRoom;
    /**
    * Leaving monitor.
    */
    private final ILeaving_Porter mLeaving;
    /**
    * Current number of customers waiting outside.
    */
    private int remainingCustomers;
    /**
    * Total number of customers.
    */
    private int totalCustomers;
    /**
    * Number of customers that entered to check-in.
    */
    private int checkInCustomers;
    
    /**
    * TPorter constructor.
    */
    private TPorter(IOutside_Porter mOutside, ICheckIn_Porter mCheckIn, IBedRoom_Porter mBedRoom, ILeaving_Porter mLeaving) {
        this.mOutside = mOutside;
        this.mCheckIn = mCheckIn;
        this.mBedRoom = mBedRoom;
        this.mLeaving = mLeaving;
        this.remainingCustomers = -1;
        this.totalCustomers = 0;
        this.checkInCustomers = 0;
        
        System.out.printf("< Porter initialized\n");
    }
    
    /**
    * Get TPorter instance.
    * 
     * @param mOutside outside monitor
     * @param mCheckIn check in monitor
     * @param mBedRoom bed room monitor
     * @param mLeaving leaving monitor
     * @return porter instance
    */
    public static Runnable getInstance(IOutside_Porter mOutside, ICheckIn_Porter mCheckIn, IBedRoom_Porter mBedRoom, ILeaving_Porter mLeaving) {
        return new TPorter(mOutside, mCheckIn, mBedRoom, mLeaving);
    }
    
    @Override
    public void run() {
        while (true) {
            mOutside.resetSimulation();
            while (true) {                          // check-in procedure
                mOutside.comeIn(6);                 // call 6 customers at a time
                mOutside.waitEntrance();

                if (!mCheckIn.waitCheckIn(mOutside.getRemainingCustomers(), mOutside.getCheckInCustomers()))    // wait for the check in queue to be empty
                    break;
            }

            while (true) {  // check-out procedure
                if (!mBedRoom.resetCheckOut(mCheckIn.getPorterTotalCustomers()))
                    break;
                
                mCheckIn.subtractPorterTotalCustomers(mLeaving.waitArrival(mCheckIn.getPorterTotalCustomers()));
                mLeaving.openDoor(mCheckIn.getPorterTotalCustomers());
            }

            System.out.println("> Porter is indicating SIMULATION COMPLETED!");
        }
    }

    /**
    * Get the number of customers remaining outside.
    * 
    * @return remaining customers
    */
    public int getRemainingCustomers() {
        return remainingCustomers;
    }

    /**
    * Set the number of customers remaining outside.
    * 
    * @param remainingCustomers remaing customers
    */    
    public void setRemainingCustomers(int remainingCustomers) {
        this.remainingCustomers = remainingCustomers;
    }

    /**
    * Get the total number of customers.
    * 
    * @return total number of customers
    */
    public int getTotalCustomers() {
        return totalCustomers;
    }

    /**
    * Set the total number of customers.
    * 
    * @param totalCustomers total number of customers
    */
    public void setTotalCustomers(int totalCustomers) {
        this.totalCustomers = totalCustomers;
    }

    /**
    * Get the number of customers that entered to check-in.
    * 
    * @return number of customers that entered to check-in
    */
    public int getcheckInCustomers() {
        return checkInCustomers;
    }

    /**
    * Set the number of customers that entered to check-in.
    * 
    * @param checkInCustomers number of customers that entered to check-in
    */
    public void setcheckInCustomers(int checkInCustomers) {
        this.checkInCustomers = checkInCustomers;
    }
}
