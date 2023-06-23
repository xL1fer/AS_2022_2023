/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package UC1.Producer.Sensor;

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
     * @param requestTimeout request time out milliseconds
     * @param maxBlock max block milliseconds
     * @param linger linger milliseconds
     */
    void signalStart(int sendingMode, int producersNumber, int requestTimeout, int maxBlock, int linger);
}
