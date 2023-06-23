/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package HCP.CheckIn;

/**
 *
 * Check In Customer interface.
 */
public interface ICheckIn_Customer {
    /**
     * Customer wait in check-in queue.
     * 
     * @param customerId customer id
     */
    void inQueue(int customerId);
    /**
     * Customer do check-in.
     * 
     * @param customerId customer id
     */
    void checkIn(int customerId);
    /**
     * Get customer bed offset.
     * 
     * @param customerId customer id
     * @return customer bed offset
     */
    int getCustomerBedOffset(int customerId);
}
