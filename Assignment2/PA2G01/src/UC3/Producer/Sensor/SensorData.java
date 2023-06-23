/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UC3.Producer.Sensor;

/**
 *
 * Sensor data class.
 */
public class SensorData {
    /**
     * Sensor id.
     */
    private String sensorId;
    /**
     * Sensor time stamp.
     */
    private int timeStamp;
    /**
     * Sensor celsius temperature.
     */
    private double cTemperature;
    
    /**
     * Sensor data default constructor.
     */
    public SensorData() {
    }
    
    /**
     * Sensor data constructor.
     * 
     * @param sensorId sensor id
     * @param timeStamp time stamp
     * @param cTemperature celsius temperature
     */
    public SensorData(String sensorId, int timeStamp, double cTemperature) {
        this.sensorId = sensorId;
        this.timeStamp = timeStamp;
        this.cTemperature = cTemperature;
    }
    
    /**
     * Get sensor id.
     * 
     * @return sensor id
     */
    public String getSensorId() {
        return sensorId;
    }

    /**
     * Set sensor id.
     * 
     * @param sensorId sensor id
     */
    public void setSensorId(String sensorId) {
        this.sensorId = sensorId;
    }

    /**
     * Get sensor time stamp.
     * 
     * @return sensor time stamp
     */
    public int getTimeStamp() {
        return timeStamp;
    }

    /**
     * Set sensor time stamp.
     * 
     * @param timeStamp sensor time stamp
     */
    public void setTimeStamp(int timeStamp) {
        this.timeStamp = timeStamp;
    }

    /**
     * Get sensor celsius temperature.
     * 
     * @return sensor celsius temperature
     */
    public double getcTemperature() {
        return cTemperature;
    }

    /**
     * Set sensor celsius temperature.
     * 
     * @param cTemperature sensor celsius temperature
     */
    public void setcTemperature(double cTemperature) {
        this.cTemperature = cTemperature;
    }

    @Override
    public String toString() {
        return "SensorData{" + "sensorId=" + sensorId + ", timeStamp=" + timeStamp + ", cTemperature=" + cTemperature + '}';
    }
}
