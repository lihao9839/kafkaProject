package com.lihao.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;
import java.util.concurrent.Future;

public class ProducerFastStart {

    public static final String brokerList = "localhost:9092";
    public static final String topic = "topic-demo";

    public static void main(String[] args){
        Properties properties = new Properties();
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("bootstrap.servers", brokerList);
        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(properties);
        ProducerRecord<String, String> record = new ProducerRecord<>(topic, "hello, lihao!");
        try{
            Future<RecordMetadata> futrue = producer.send(record);
            RecordMetadata metadata = futrue.get();
            System.out.println(metadata.topic() + "-" + metadata.partition()+
                    ":" + metadata.offset());
        }catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            producer.close();
        }

    }
}
