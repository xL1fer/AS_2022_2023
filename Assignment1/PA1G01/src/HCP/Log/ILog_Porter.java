/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package HCP.Log;

/**
 *
 * Log Porter interface.
 */
public interface ILog_Porter {
    /**
     * Porter log check-in queue.
     * 
     * @param queue check-in queue
     */
    void porter_logQueue(int queue[]);
    /**
     * Porter log check-in front door.
     * 
     * @param frontDoorOpen front door status
     */
    void porter_logDoorCheckIn(boolean frontDoorOpen);
    /**
     * Porter log check-out front door.
     * 
     * @param frontDoorOpen front door status
     */
    void porter_logDoorCheckOut(boolean frontDoorOpen);
    /**
     * Porter log idle.
     */
    void porter_logIdle();
    /**
     * Porter log outside queue.
     * 
     * @param queue outside queue
     * @param customers number of customers outside
     */
    void porter_logOutside(int queue[], int customers);
}
