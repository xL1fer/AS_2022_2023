/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UC3.Producer;

import java.text.SimpleDateFormat;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.Date;
import org.apache.kafka.clients.consumer.RangeAssignor;
import org.apache.kafka.clients.consumer.RoundRobinAssignor;
import org.apache.kafka.clients.consumer.StickyAssignor;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import UC3.Producer.Sensor.ISensor_Producer;
import UC3.Producer.Sensor.SensorData;

/**
 * Message call back class.
 * https://progressivecoder.com/kafka-producer-example/
 */
class MessageCallBack implements Callback {
    @Override
    public void onCompletion(RecordMetadata recordMetadata, Exception e) {
        if (e != null) {
            System.err.println("Exception: " + e.getMessage());
        }
    }
}

/**
 *
 * Producer thread class.
 */
public class TProducer implements Runnable {
    /**
     * Producer id.
     */
    private final int producerId;
    /**
     * Sensor monitor object instance.
     */
    private final ISensor_Producer mSensor;
    /**
     * Topic name.
     */
    private final String topic;
    /**
     * Date format.
     */
    private final SimpleDateFormat sdf;
    
    /**
     * Kafka logger object.
     */
    private final Logger kafkaLogger;
    
    /**
     * Kafka producer object.
     */
    private KafkaProducer<Integer, String> kafkaProducer;
    /**
     * Properties object.
     */
    private final Properties properties;
    
    /**
     * TProducer class constructor.
     * 
     * @param producerId producer id
     * @param mSensor sensor monitor object
     * @param properties producer properties
     */
    private TProducer(int producerId, ISensor_Producer mSensor, Properties properties) {
        this.producerId = producerId;
        this.mSensor = mSensor;
        this.topic = "Sensor";
        this.sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        
        this.kafkaLogger = LoggerFactory.getLogger(TProducer.class.getName());
        
        this.properties = properties;
        
        System.out.printf("< Producer %d initialized\n", this.producerId);
    }
    
    /**
     * Get TProducer instance.
     * 
     * @param producerId producer id
     * @param mSensor sensor monitor object
     * @param properties producer properties
     * @return producer instance
     */
    public static Runnable getInstance(int producerId, ISensor_Producer mSensor, Properties properties) {
        return new TProducer(producerId, mSensor, properties);
    }
    
    @Override
    public void run() {
        // initialize sensor data
        this.mSensor.parseSensorData(this.producerId);
        
        // wait for the start button
        int sendingMode = this.mSensor.waitStart();
        
        if (sendingMode == 0)
            this.properties.put("partition.assignment.strategy", RangeAssignor.class.getName());
        else if (sendingMode == 1)
            this.properties.put("partition.assignment.strategy", RoundRobinAssignor.class.getName());
        else
            this.properties.put("partition.assignment.strategy", StickyAssignor.class.getName());
        
        this.kafkaProducer = new KafkaProducer(properties);
        
        try {
            SensorData sData;
            while ((sData = mSensor.getSensorData(producerId)) != null) {
                // fire and forget
                if (sendingMode == 0) {
                    try {
                        this.kafkaProducer.send(new ProducerRecord<>(topic, sData.getTimeStamp(),
                                Double.toString(sData.getcTemperature())));
                        System.out.println("(FnF) Sent message: (" + sData.getTimeStamp() + ", " + sData.getcTemperature() + ")");
                    } catch (Exception e) {
                        System.err.println("Exception: " + e.getMessage());
                    }
                    
                    this.kafkaProducer.flush();
                }
                // synchronous
                else if (sendingMode == 1) {
                    try {
                        this.kafkaProducer.send(new ProducerRecord<>(topic, sData.getTimeStamp(),
                                Double.toString(sData.getcTemperature()))).get();
                        System.out.println("(Sync) Sent message: (" + sData.getTimeStamp() + ", " + sData.getcTemperature() + ")");
                    } catch (InterruptedException | ExecutionException e) {
                        System.err.println("Exception: " + e.getMessage());
                    }
                }
                // asynchronous
                else {
                    try {
                        this.kafkaProducer.send(new ProducerRecord<>(topic, sData.getTimeStamp(),
                                Double.toString(sData.getcTemperature())), new MessageCallBack());
                        System.out.println("(Async) Sent message: (" + sData.getTimeStamp() + ", " + sData.getcTemperature() + ")");
                    } catch(Exception e) {
                        System.err.println("Exception: " + e.getMessage());
                    }
                }
                
                // gui text display
                javax.swing.JTextField displayTextField;
                if (this.producerId == 0)
                    displayTextField = PProducer.displayTextField1;
                else if (this.producerId == 1)
                    displayTextField = PProducer.displayTextField2;
                else
                    displayTextField = PProducer.displayTextField3;
                displayTextField.setText(this.sdf.format(new Date()) + " > " + "(" + sData.getTimeStamp() + ", " + sData.getcTemperature() + ")\n");
            }
        } finally {
            this.kafkaProducer.close();
        }
    }
}
