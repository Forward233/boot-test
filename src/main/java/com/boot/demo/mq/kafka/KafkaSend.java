package com.boot.demo.mq.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * @author: yhl
 * @DateTime: 2021/6/19 12:47
 * @Description:
 */
public class KafkaSend {

    public static void main(String[] args) {
        Properties kafkaProps = new Properties();
        kafkaProps.put("bootstrap.servers", "127.0.0.1:9092");
        kafkaProps.put("key.serializer", StringSerializer.class);
        kafkaProps.put("value.serializer", StringSerializer.class);
        KafkaProducer<String, String> producer = new KafkaProducer<>(kafkaProps);
        ProducerRecord<String, String> kvProducerRecord = new ProducerRecord<>("c", "1", "1");
        producer.send(kvProducerRecord);
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
