/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package HCP.MealRoom;

/**
 *
 * Meal Room Waiter interface.
 */
public interface IMealRoom_Waiter {
    /**
     * Waiter waits for customers.
     * 
     * @param waiterTotalCustomers waiter reference to total customers
     * @return number of customers that are going to be served
     */
    int waitMealRoomCustomers(int waiterTotalCustomers);
    /**
     * Waiter serves breakfast to each customer.
     * 
     * @return true if there are still more customers to be served
     */
    boolean serveBreakfast();
}
