/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package HCP.MealRoom;

/**
 *
 * Meal Room Customer interface.
 */
public interface IMealRoom_Customer {
    /**
     * Customer wait for breakfast.
     * 
     * @param customerId customer id
     */
    void waitBreakfast(int customerId);
    /**
     * Customer take breakast.
     * 
     * @param customerId customer id
     */
    void takeBreakfast(int customerId);
}
