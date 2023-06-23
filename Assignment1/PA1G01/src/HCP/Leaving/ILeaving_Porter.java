/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package HCP.Leaving;

/**
 *
 * Leaving Porter interface.
 */
public interface ILeaving_Porter {
    /**
     * Porter wait for customers' arrival.
     * 
     * @param porterTotalCustomers porter total customers reference
     * @return number of remaining customers
     */
    int waitArrival(int porterTotalCustomers);
    /**
     * Porter open front door for customers to exit the hostel.
     * 
     * @param porterTotalCustomers porter total customers reference
     */
    void openDoor(int porterTotalCustomers);
}
