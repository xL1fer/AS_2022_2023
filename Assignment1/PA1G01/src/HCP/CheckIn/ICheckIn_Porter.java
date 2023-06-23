/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package HCP.CheckIn;

/**
 *
 * Check In Porter interface.
 */
public interface ICheckIn_Porter {
    /**
     * Porter wait check-in completion.
     * 
     * @param remainingCustomers remaining customer
     * @param checkInCustomers check in customers
     * @return true if check-in is not yet completed
     */
    boolean waitCheckIn(int remainingCustomers, int checkInCustomers);
    /**
     * Get number of total customers.
     * 
     * @return number of total customers
     */
    public int getPorterTotalCustomers();
    /**
     * Subtract number of total customers.
     * 
     * @param nCustomers number of customers to subtract
     */
    public void subtractPorterTotalCustomers(int nCustomers);
}
