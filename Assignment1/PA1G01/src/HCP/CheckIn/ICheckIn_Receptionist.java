/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package HCP.CheckIn;

/**
 *
 * Check In Receptionist interface.
 */
public interface ICheckIn_Receptionist {
    /**
     * Receptionist wait for customers' arrival.
     * 
     * @param receptionistId receptionist's id
     */
    void waitArrival(int receptionistId);
    /**
     * Receptionist attend customer.
     * 
     * @param receptionistId receptionist's id
     */
    void attendCustomer(int receptionistId);
}
