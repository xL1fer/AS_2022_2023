/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package HCP.Log;

/**
 *
 * Log Customer interface.
 */
public interface ILog_Customer {
    /**
     * Customer log reception.
     * 
     * @param customerId customer id
     * @param rPos reception position
     */
    void customer_logReception(int customerId, int rPos);
    /**
     * Customer log floor.
     * 
     * @param customerId customer id
     * @param bedOffset bed offset
     */
    void customer_logFloor(int customerId, int bedOffset);
    /**
     * Customer log bathroom.
     * 
     * @param customerId customer id
     * @param bedRoomNumber bedroom number
     */
    void customer_logBathroom(int customerId, int bedRoomNumber);
    /**
     * Customer log mealroom.
     * 
     * @param customerId customer id
     * @param seatNumber table seat number
     */
    void customer_logMealroom(int customerId, int seatNumber);
    /**
     * Customer log breakfast.
     * 
     * @param customerId customer id
     * @param seatNumber table seat number
     */
    void customer_logBreakfast(int customerId, int seatNumber);
    /**
     * Customer log leaving.
     * 
     * @param customerId customer id
     * @param leavingPos leaving hall position
     */
    void customer_logLeaving(int customerId, int leavingPos);
    /**
     * Customer log outside.
     * 
     * @param customerId customer id
     * @param outsidePos outside position
     */
    void customer_logOutside(int customerId, int outsidePos);
}
