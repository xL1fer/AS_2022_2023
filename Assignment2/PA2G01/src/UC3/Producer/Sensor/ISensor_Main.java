/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package UC3.Producer.Sensor;

/**
 *
 * Sensor Main interface.
 */
public interface ISensor_Main {
    /**
     * Signal start production.
     * 
     * @param sendingMode sending mode
     * @param producersNumber producers number
     */
    void signalStart(int sendingMode, int producersNumber);
}
