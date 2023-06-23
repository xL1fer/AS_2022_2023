/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Utils;

/**
 *
 * Message interface.
 */
public interface IMessage {
    /**
     * Get message's action.
     * 
     * @return action to perform
     */
    String getAction();
    /**
     * Get message's customers number.
     * 
     * @return customers number
     */
    int getCustomers();
    /**
     * Get message's check-in time.
     * 
     * @return check-in time
     */
    int getTimeCheckIn();
    /**
     * Get message's bathroom time.
     * 
     * @return bathroom time
     */
    int getTimeBathroom();
    /**
     * Get message's breakfast time.
     * 
     * @return breakfast time
     */
    int getTimeBreakfast();
    
    /**
     * Print message's content.
     */
    void printMessage();
}
