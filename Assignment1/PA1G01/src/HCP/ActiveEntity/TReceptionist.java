/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HCP.ActiveEntity;

import HCP.CheckIn.ICheckIn_Receptionist;

/**
 *
 * Receptionist thread class behaviour.
 */
public class TReceptionist implements Runnable {
    /**
    * Receptionist identifier.
    */
    private final int receptionistId;
    /**
    * CheckIn monitor.
    */
    private final ICheckIn_Receptionist mCheckIn;
    /**
    * Current number of customers in the checkin's queue.
    */
    private int customersInQueue;

    /**
    * TReceptionist constructor.
    */
    private TReceptionist(int receptionistId, ICheckIn_Receptionist mCheckIn) {
        this.receptionistId = receptionistId;
        this.mCheckIn = mCheckIn;
        this.customersInQueue = 0;
        
        System.out.printf("< Receptionist %d initialized\n", this.receptionistId);
    }
    
    /**
    * Get TReceptionist instance.
    * 
     * @param receptionistId receptionist id
     * @param mCheckIn check in monitor
     * @return receptionist instance
    */
    public static Runnable getInstance(int receptionistId,  ICheckIn_Receptionist mCheckIn) {
        return new TReceptionist(receptionistId, mCheckIn);
    }
    
    @Override
    public void run() {
        while (true) {
            mCheckIn.waitArrival(receptionistId); // wait for customers arrival

            mCheckIn.attendCustomer(receptionistId);
        }
    }
    
    /**
    * Get the current number of customers in the checkin's queue.
    * 
    * @return number of customers in queue
    */
    public int getCustomersInQueue() {
        return customersInQueue;
    }

    /**
    * Set the current number of customers in the checkin's queue.
    * 
    * @param customersInQueue number of customers in queue
    */
    public void setCustomersInQueue(int customersInQueue) {
        this.customersInQueue = customersInQueue;
    }
    
    /**
    * Add a costumer to the counter of the customers in queue.
    */
    public void addCustomersInQueue() {
        this.customersInQueue++;
    }
}
