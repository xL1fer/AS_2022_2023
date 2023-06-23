/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package HCP.CheckIn;

/**
 *
 * Check In Waiter interface.
 */
public interface ICheckIn_Waiter {
    /**
     * Waiter awaits customers to check in the hostel.
     */
    void waitCheckInCustomers();
    /**
     * Get number of total customers.
     * 
     * @return number of total customers
     */
    public int getWaiterTotalCustomers();
    /**
     * Subtract number of total customers.
     * 
     * @param nCustomers number of customers to subtract
     */
    public void subtractWaiterTotalCustomers(int nCustomers);
}
