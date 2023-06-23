/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package HCP.Log;

/**
 *
 * Log CCP interface.
 */
public interface ILog_ControlCentreProxy {
    /**
     * CCP log iddle.
     * 
     * @param totalCustomers total customers
     */
    void ccp_logIdle(int totalCustomers);
    /**
     * CCP log run.
     */
    void ccp_logRun();
    /**
     * CCP log suspended.
     * 
     * @param checkin check-in phase flag
     */
    void ccp_logSuspend(boolean checkin);
    /**
     * CCP log resume.
     * 
     * @param checkin check-in phase flag
     */
    void ccp_logResume(boolean checkin);
    /**
     * CCP log manual.
     * 
     * @param checkin check-in phase flag
     */
    void ccp_logManual(boolean checkin);
    /**
     * CCP log step.
     * 
     * @param checkin check-in phase flag
     */
    void ccp_logStep(boolean checkin);
    /**
     * CCP log auto.
     * 
     * @param checkin check-in phase flag
     */
    void ccp_logAuto(boolean checkin);
    /**
     * CCP log check-in.
     */
    void ccp_logCheckIn();
    /**
     * @param floorNumber floor number
     */
    void ccp_logCheckOut(int floorNumber);
    
    /**
     * CCP log starting.
     */
    void ccp_logOpen();
    /**
     * CCP log ending.
     */
    void ccp_logClose();
}
