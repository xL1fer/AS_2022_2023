/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UC4.Producer;

import java.text.SimpleDateFormat;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.Date;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import UC4.Producer.Sensor.ISensor_Producer;
import UC4.Producer.Sensor.SensorData;

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
     * Total records.
     */
    private int totalRecords;

    /**
     * Kafka producer object.
     */
    private final KafkaProducer<Integer, String> kafkaProducer;
    /**
     * Kafka logger object.
     */
    private final Logger kafkaLogger;
    
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
        this.totalRecords = 0;
        
        this.kafkaProducer = new KafkaProducer(properties);
        this.kafkaLogger = LoggerFactory.getLogger(TProducer.class.getName());
        
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
        
        try {
            SensorData sData;
            while ((sData = mSensor.getSensorData(producerId)) != null) {
                this.totalRecords++;
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
                PProducer.displayTextField.setText(this.sdf.format(new Date()) + " > " + "(" + sData.getTimeStamp() + ", " + sData.getcTemperature() + ")\n");
            
                PProducer.totalRecordsLabel.setText("Total Records: " + this.totalRecords);
            }
        } finally {
            this.kafkaProducer.close();
        }
    }
}
