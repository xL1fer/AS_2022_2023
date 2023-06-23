/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package HCP.BedRoom;

/**
 *
 * Bed Room Control Centre Proxy interface.
 */
public interface IBedRoom_ControlCentreProxy {
    /**
     * CCP start checkout.
     */
    void startCheckOut();
    /**
     * CCP set time bathroom.
     * 
     * @param timeBathroom time taken in the bathroom by each customer
     */
    void setTimeBathroom(int timeBathroom);
    
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
