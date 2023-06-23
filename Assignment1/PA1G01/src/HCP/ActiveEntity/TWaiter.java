/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HCP.ActiveEntity;

import HCP.CheckIn.ICheckIn_Waiter;
import HCP.MealRoom.IMealRoom_Waiter;

/**
 *
 * Waiter thread class behaviour.
 */
public class TWaiter implements Runnable {
    /**
    * CheckIn monitor.
    */
    private final ICheckIn_Waiter mCheckIn;
    /**
    * MealRoom monitor.
    */
    private final IMealRoom_Waiter mMealRoom;
    /**
    * Total number of customers.
    */
    private int totalCustomers;
    /**
    * Wait until all customers of a certain floor arrive to become true.
    */
    private boolean servingFloor;
    
    /**
    * TWaiter constructor.
    */
    private TWaiter(ICheckIn_Waiter mCheckIn, IMealRoom_Waiter mMealRoom) {
        this.mCheckIn = mCheckIn;
        this.mMealRoom = mMealRoom;
        this.totalCustomers = 0;
        this.servingFloor = false;
        
        System.out.printf("< Waiter initialized\n");
    }
    
    /**
    * Get TWaiter instance.
    * 
     * @param mCheckIn check in monitor
     * @param mMealRoom meal room monitor
     * @return waiter instance
    */
    public static Runnable getInstance(ICheckIn_Waiter mCheckIn, IMealRoom_Waiter mMealRoom) {
        return new TWaiter(mCheckIn, mMealRoom);
    }
    
    @Override
    public void run() {
        while (true) {
            mCheckIn.waitCheckInCustomers();

            while(true) {
                int nextCustomers = mMealRoom.waitMealRoomCustomers(mCheckIn.getWaiterTotalCustomers());
                
                if (nextCustomers == 0)
                    break;
                
                mCheckIn.subtractWaiterTotalCustomers(nextCustomers);

                while(true) {
                    if(!mMealRoom.serveBreakfast())
                        break;
                }
            }
        }
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
    * Return the serving service's state.
    * 
    * @return serving service's state
    */
    public boolean isServingFloor() {
        return servingFloor;
    }

    /**
    * Set the serving service's state.
    * 
    * @param servingFloor serving service's state
    */
    public void setServingFloor(boolean servingFloor) {
        this.servingFloor = servingFloor;
    }
}
