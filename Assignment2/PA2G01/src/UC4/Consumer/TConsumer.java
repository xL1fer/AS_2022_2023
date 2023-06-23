/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UC4.Consumer;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Collections;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * Consumer thread class.
 */
public class TConsumer implements Runnable {
    /**
     * Consumer id.
     */
    private final int consumerId;
    /**
     * Topic name.
     */
    private final String topic;
    /**
     * Date format.
     */
    private final SimpleDateFormat sdf;
    /**
     * Thread running flag.
     */
    private final AtomicBoolean running;
    
    /**
     * Kafka consumer object.
     */
    private final KafkaConsumer<Integer, String> kafkaConsumer;
    /**
     * Kafka logger object.
     */
    private final Logger kafkaLogger;
    
    /**
     * TConsumer class constructor.
     * 
     * @param consumerId consumer id
     * @param properties consumer properties
     */
    public TConsumer(int consumerId, Properties properties) {
        this.consumerId = consumerId;
        this.topic = "Sensor";
        this.sdf = new SimpleDateFormat("HH:mm:ss"); // "yyyy-MM-dd HH:mm:ss"
        this.running = new AtomicBoolean(false);
        
        this.kafkaConsumer = new KafkaConsumer(properties);
        this.kafkaLogger = LoggerFactory.getLogger(TConsumer.class.getName());
        
        this.kafkaConsumer.subscribe(Collections.singletonList(topic));
        
        System.out.printf("< Consumer %d initialized\n", this.consumerId);
    }
    
    /**
     * Get TConsumer instance.
     * 
     * @param consumerId consumer id
     * @param properties consumer properties
     * @return consumer instance
     */
    public static Runnable getInstance(int consumerId, Properties properties) {
        return new TConsumer(consumerId, properties);
    }
    
    /**
     * Terminate consumer thread.
     */
    public void terminate() {
        this.running.set(false);
    }

    @Override
    public void run() {
        this.running.set(true);
        int giveUp = 100;
        int noRecordsCount = 0;
        
        long startTime = System.currentTimeMillis();
        
        try {
            while (this.running.get()) {
                ConsumerRecords<Integer, String> records = this.kafkaConsumer.poll(Duration.ofMillis(1000));

                if (records.count() == 0) {
                    noRecordsCount++;
                    if (noRecordsCount > giveUp) break;
                    else continue;
                }

                records.forEach(record -> {
                    //System.out.printf("Consumer Record:(%d, %s, %d, %d)\n",
                    //        record.key(), record.value(),
                    //        record.partition(), record.offset());
                    //PConsumer.displayTextArea.append(this.sdf.format(new Date()) + " > ");
                    //PConsumer.displayTextArea.append(String.format("(%d, %s, %d, %d)\n", record.key(), record.value(), record.partition(), record.offset()));
                    
                    // gui text display
                    javax.swing.JTextField displayTextField;
                    if (this.consumerId == 0)
                        displayTextField = PConsumer.displayTextField1;
                    else if (this.consumerId == 1)
                        displayTextField = PConsumer.displayTextField2;
                    else
                        displayTextField = PConsumer.displayTextField3;
                    
                    displayTextField.setText(this.sdf.format(new Date()) + " > " +
                            String.format("(%d, %s, %d, %d)\n", record.key(), record.value(), record.partition(), record.offset()));
                });
            }
        } catch (WakeupException e) {
            System.err.println("WakeupException: " + e.getMessage());
        } finally {
            this.kafkaConsumer.close();
        }
        
        // gui text display
        javax.swing.JLabel elapsedTimeLabel;
        if (this.consumerId == 0)
            elapsedTimeLabel = PConsumer.elapsedTimeLabel1;
        else if (this.consumerId == 1)
            elapsedTimeLabel = PConsumer.elapsedTimeLabel2;
        else
            elapsedTimeLabel = PConsumer.elapsedTimeLabel3;
        
        long elapsedTime = System.currentTimeMillis() - startTime;
        if (noRecordsCount > giveUp)
            elapsedTime -= giveUp * 1000;
        elapsedTimeLabel.setText("Elapsed Time: " + elapsedTime / 1000 + " (s)");
        
        System.out.println("Consumer " + this.consumerId +  " finished");
    }    
}
