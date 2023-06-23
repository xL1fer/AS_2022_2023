/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package HCP.Outside;

/**
 *
 * Outside Control Centre Proxy interface.
 */
public interface IOutside_ControlCentreProxy {
    /**
     * CCP signal next simulation.
     * 
     * @param totalCostumers number of customers participating in the simulation
     */
    void nextSimulation(int totalCostumers);
    /**
     * CCP signal check-in.
     */
    void startCheckIn();
    
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
