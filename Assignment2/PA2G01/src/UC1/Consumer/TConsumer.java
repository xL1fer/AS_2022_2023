/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UC1.Consumer;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Collections;
import java.util.Date;
import java.util.Properties;
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
    private TConsumer(int consumerId, Properties properties) {
        this.consumerId = consumerId;
        this.topic = "Sensor";
        this.sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        
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

    @Override
    public void run() {
        int giveUp = 100;
        int noRecordsCount = 0;
        try {
            while (true) {
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
                    PConsumer.displayTextField.setText(this.sdf.format(new Date()) + " > " +
                            String.format("(%d, %s, %d, %d)\n", record.key(), record.value(), record.partition(), record.offset()));
                });
            }
        } catch (WakeupException e) {
            System.err.println("WakeupException: " + e.getMessage());
        } finally {
            this.kafkaConsumer.close();
        }
    }    
}
