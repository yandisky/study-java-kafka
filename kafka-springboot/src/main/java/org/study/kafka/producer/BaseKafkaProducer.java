package org.study.kafka.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.List;

@Slf4j
@Component
public class BaseKafkaProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public BaseKafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Async
    public void send(String topic, String key, String msg) {
        send(topic, key, 0, msg);
    }

    public void send(String topic, String key, Integer partition, String msg) {
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, partition, key, msg);
        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onFailure(Throwable throwable) {
                log.debug("kafka producer fail:{}", throwable.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, String> sendResult) {
                log.debug("kafka producer success:{}", sendResult.toString());
            }
        });
    }

    @Async
    public void sendBatch(String topic, String key, List<String> msgList) {
        msgList.forEach(item -> {
            send(topic, key, item);
        });
    }
}
