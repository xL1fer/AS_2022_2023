/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package HCP.Outside;

/**
 *
 * Outside Customer interface.
 */
public interface IOutside_Customer {
    /**
     * Customer walk around outside the hostel.
     * 
     * @param customorId customer id
     */
    void walkAround(int customorId);
    /**
     * Customer wait turn to enter the hostel.
     * 
     * @param customerId customer id
     */
    void waitTurn(int customerId);
}
