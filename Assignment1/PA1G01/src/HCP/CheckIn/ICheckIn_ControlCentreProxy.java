/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package HCP.CheckIn;

/**
 *
 * Check In Control Centre Proxy interface.
 */
public interface ICheckIn_ControlCentreProxy {
    /**
     * CCP set time bathroom.
     * 
     * @param timeCheckIn time taken in the checkin by each customer
     */
    void setTimeCheckIn(int timeCheckIn);
    
    /**
     * CCP suspend simulation.
     */
    void suspendSimulation();
    /**
     * CCP resume simulation.
     * 
     * @param resume flag to indicate if it is a resume or step simulation action
     */
    void resumeSimulation(boolean resume);
    /**
     * CCP manual simulation.
     */
    void manualSimulation();
    /**
     * CCP auto simulation.
     */
    void autoSimulation();
}
