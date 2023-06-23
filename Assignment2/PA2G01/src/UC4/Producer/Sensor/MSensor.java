/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UC4.Producer.Sensor;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * Sensor monitor class.
 */
public class MSensor implements ISensor {
    /**
     * Reentrantlock synchronization object.
     */
    private final ReentrantLock rl;
    /**
     * Wait start signal condition.
     */
    private final Condition cWaitStart;
    /**
     * Wait finish signal condition.
     */
    private final Condition cWaitFinish;
    /**
     * Sensor data array.
     */
    private final ArrayList<SensorData> sensor;
    /**
     * Sensor data parsed flag.
     */
    private boolean sensorDataParsed;
    /**
     * Sensor data iterator.
     */
    private int sensorIter;
    /**
     * Sending mode.
     */
    private int sendingMode;
    /**
     * Producers number.
     */
    private int producersNumber;
    /**
     * Remaining producers.
     */
    private int producersRemainig;
    
    /**
     * Sensor monitor class constructor.
     */
    private MSensor() {
        this.rl = new ReentrantLock();
        this.cWaitStart = rl.newCondition();
        this.cWaitFinish = rl.newCondition();
        this.sensor = new ArrayList<>();
        this.sensorDataParsed = false;
        this.sensorIter = 0;
        this.sendingMode = 0;
        this.producersNumber = 0;
        this.producersRemainig = 0;
    }
    
    /**
     * Create instance of sensor monitor.
     * 
     * @return sensor monitor instance
     */
    public static ISensor getInstance() {
        return new MSensor();
    }
    
    @Override
    public void signalStart(int sendingMode, int producersNumber) {
        try {
            this.rl.lock();
            
            this.sendingMode = sendingMode;
            this.producersNumber = producersNumber;
            this.producersRemainig = producersNumber;
            this.cWaitStart.signalAll();
        } finally {
            this.rl.unlock();
        }
    }

    @Override
    public void parseSensorData(int producerId) {
        try {
            this.rl.lock();
            
            // check if sensor data was already parsed
            if (this.sensorDataParsed)
                return;
            this.sensorDataParsed = true;
            
            try {
                File sensorFile = new File("Sensor.txt");
                Scanner fileReader = new Scanner(sensorFile);
                
                int i = 0;
                SensorData sData = new SensorData();
                while (fileReader.hasNextLine()) {
                    String data = fileReader.nextLine();
                    //System.out.println(data);
                    
                    if (i % 3 == 0) {
                        sData = new SensorData();
                        sData.setSensorId(data);
                    }
                    else if (i % 3 == 1)
                        sData.setTimeStamp(Integer.parseInt(data));
                    else if (i % 3 == 2) {
                        sData.setcTemperature(Double.parseDouble(data));
                        sensor.add(sData);
                    }
                    i++;
                }
            } catch (FileNotFoundException e) {
                System.err.println("FileNotFoundException: " + e.getMessage());
            }
            
            System.out.println("> Producer " + producerId + " parsed the file");
        } finally {
            this.rl.unlock();
        }
    }
    
    @Override
    public int waitStart() {
        try {
            this.rl.lock();
            
            while (this.producersNumber == 0) {
                try {
                    this.cWaitStart.await();
                } catch (InterruptedException ex){}
            }
            this.producersNumber--;
        } finally {
            this.rl.unlock();
        }
        
        return this.sendingMode;
    }
    
    @Override
    public SensorData getSensorData(int producerId) {
        try {
            this.rl.lock();
            if (this.sensorIter >= this.sensor.size())
                return null;
            return this.sensor.get(this.sensorIter++);
        } finally {
            this.rl.unlock();
        }
    }
    
    @Override
    public int waitFinish() {
        int pos;
        try {
            this.rl.lock();
            pos = --this.producersRemainig;
            while (this.producersRemainig != 0) {
                try {
                    this.cWaitFinish.await();
                } catch (InterruptedException ex){}
            }
            this.cWaitFinish.signalAll();
        } finally {
            this.rl.unlock();
        }
        
        return pos;
    }
}
