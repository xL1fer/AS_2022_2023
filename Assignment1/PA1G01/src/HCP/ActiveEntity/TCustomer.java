/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HCP.ActiveEntity;

import HCP.Outside.IOutside_Customer;
import HCP.CheckIn.ICheckIn_Customer;
import HCP.BedRoom.IBedRoom_Customer;
import HCP.MealRoom.IMealRoom_Customer;
import HCP.Leaving.ILeaving_Customer;

/**
 *
 * Customer thread class behaviour.
 */
public class TCustomer implements Runnable {
    /**
    * Customer identifier.
    */
    private final int customerId;
    /**
    * Outside monitor.
    */
    private final IOutside_Customer mOutside;
    /**
    * CheckIn monitor.
    */
    private final ICheckIn_Customer mCheckIn;
    /**
    * BedRoom monitor.
    */
    private final IBedRoom_Customer mBedRoom;
    /**
    * MealRoom monitor.
    */
    private final IMealRoom_Customer mMealRoom;
    /**
    * Leaving monitor.
    */
    private final ILeaving_Customer mLeaving;
    /**
    * Floor's number where customer will stay.
    */
    private int floorNumber = 0;
    /**
    * Bedroom's number where customer will stay.
    */
    private int bedRoomNumber = 0;
    /**
    * Bed's number where customer will stay.
    */
    private int bedNumber = -1;
    /**
    * Checkin position where customer will wait to be attended.
    */
    private int queuePos = -1;
    
    /**
    * TCostumer constructor.
    */
    private TCustomer(int customerId, IOutside_Customer mOutside, ICheckIn_Customer mCheckIn, IBedRoom_Customer mBedRoom, IMealRoom_Customer mMealRoom, ILeaving_Customer mLeaving) {
        this.customerId = customerId;
        this.mOutside = mOutside;
        this.mCheckIn = mCheckIn;
        this.mBedRoom = mBedRoom;
        this.mMealRoom = mMealRoom;
        this.mLeaving = mLeaving;
        
        System.out.printf("< Customer %d initialized\n", this.customerId);
    }
    
    /**
    * Get TCostumer instance.
    * 
     * @param customerId customer id
     * @param mOutside outside monitor
     * @param mCheckIn check in monitor
     * @param mBedRoom bed room monitor
     * @param mMealRoom meal room monitor
     * @param mLeaving leaving monitor
     * @return customer instance
    */
    public static Runnable getInstance(int customerId, IOutside_Customer mOutside, ICheckIn_Customer mCheckIn, IBedRoom_Customer mBedRoom, IMealRoom_Customer mMealRoom, ILeaving_Customer mLeaving) {
        return new TCustomer(customerId, mOutside, mCheckIn, mBedRoom, mMealRoom, mLeaving);
    }
    
    @Override
    public void run() {
        while (true) {
            mOutside.walkAround(this.customerId);
            mOutside.waitTurn(this.customerId);

            mCheckIn.inQueue(this.customerId);
            mCheckIn.checkIn(this.customerId);

            mBedRoom.goToBed(this.customerId, mCheckIn.getCustomerBedOffset(this.customerId));
            mBedRoom.waitBathroom(this.customerId, mCheckIn.getCustomerBedOffset(this.customerId));
            mBedRoom.useBathroom(this.customerId, mCheckIn.getCustomerBedOffset(this.customerId));

            mMealRoom.waitBreakfast(this.customerId);
            mMealRoom.takeBreakfast(this.customerId);
            
            mLeaving.waitLeave(this.customerId);
        }
    }
    
    /**
    * Get customer identifier.
    * 
    * @return customer identifier
    */
    public int getCustomerId() {
        return customerId;
    }
    
    /**
    * Get floor number.
    * 
    * @return floor number
    */
    public int getFloorNumber() {
        return floorNumber;
    }

    /**
    * Set floor number.
    * 
    * @param floorNumber floor number
    */
    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    /**
    * Get bedroom number.
    * 
    * @return bedroom number 
    */
    public int getBedRoomNumber() {
        return bedRoomNumber;
    }

    /**
    * Set bedroom number.
    * 
    * @param bedRoomNumber bedroom number
    */
    public void setBedRoomNumber(int bedRoomNumber) {
        this.bedRoomNumber = bedRoomNumber;
    }
    
    /**
    * Get position in the checkin's queue.
    * 
    * @return checkin's position 
    */
    public int getQueuePos() {
        return queuePos;
    }

    /**
    * Set position in the checkin's queue.
    * 
     * @param queuePos checkin's position
    */
    public void setQueuePos(int queuePos) {
        this.queuePos = queuePos;
    }

    /**
    * Get bed number.
    * 
    * @return bed number;
    */
    public int getBedNumber() {
        return bedNumber;
    }

    /**
    * Set bed number.
    * 
     * @param bedNumber bed number
    */
    public void setBedNumber(int bedNumber) {
        this.bedNumber = bedNumber;
    }
}
