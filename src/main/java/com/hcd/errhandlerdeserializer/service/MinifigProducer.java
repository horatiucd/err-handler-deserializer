package com.hcd.errhandlerdeserializer.service;

import com.hcd.errhandlerdeserializer.domain.MinifigRequest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class MinifigProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public MinifigProducer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(MinifigRequest request) {
        kafkaTemplate.send("minifig-request", request);
    }
}
