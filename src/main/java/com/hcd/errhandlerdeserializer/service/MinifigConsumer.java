package com.hcd.errhandlerdeserializer.service;

import com.hcd.errhandlerdeserializer.domain.MinifigRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MinifigConsumer {

    @KafkaListener(topics = "${topic.minifig.request}", groupId = "${topic.minifig.request.group.id}")
    public void onMessage(@Payload MinifigRequest request) {
        log.info("New minifig request received - {}.", request);
    }
}
