package org.study.kafka.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DemoConsumer {
    //    @KafkaListener(topics = "demo", groupId = "kafka-demo")
    @KafkaListener(topicPartitions = {
            @TopicPartition(topic = "demo", partitions = {"0"})
    })
    public void listen(ConsumerRecord<String, String> record) {
        System.out.println("topic:" + record.topic() + ",key:" + record.key() + ",value:" + record.value());
        System.out.println("kafka报文信息:" + record.value());
    }
}
