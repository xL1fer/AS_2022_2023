/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package HCP.Outside;

/**
 *
 * Outside Porter interface.
 */
public interface IOutside_Porter {
    /**
     * Porter reset simulation.
     */
    void resetSimulation();
    /**
     * Porter call customers to enter the hostel.
     * 
     * @param count number of customers
     */
    void comeIn(int count);
    /**
     * Porter wait for customers to enter the hostel.
     */
    void waitEntrance();
    /**
     * Get number of remaining customers.
     * 
     * @return number of remaining customers
     */
    int getRemainingCustomers();
    /**
     * Get number of checkIn customers.
     * 
     * @return number of checkIn customers
     */
    int getCheckInCustomers();
}
