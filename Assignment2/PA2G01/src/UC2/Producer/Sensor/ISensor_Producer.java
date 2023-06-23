/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package UC2.Producer.Sensor;

/**
 *
 * Sensor Producer interface.
 */
public interface ISensor_Producer {
    /**
     * Parse sensor data file.
     * 
     * @param producerId producer id
     */
    void parseSensorData(int producerId);
    /**
     * Wait start signal.
     * 
     * @return simulation sending mode
     */
    int waitStart();
    /**
     * Get next sensor data.
     * 
     * @param producerId producer id
     * @return sensor data object
     */
    SensorData getSensorData(int producerId);
    /**
     * Wait all producers finish.
     * 
     * @return finishing position
     */
    int waitFinish();
}