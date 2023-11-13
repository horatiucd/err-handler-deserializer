package com.hcd.errhandlerdeserializer.listener;

import com.hcd.errhandlerdeserializer.domain.Minifig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class MinifigListener {

    private static final Logger LOG = LoggerFactory.getLogger(MinifigListener.class);

    @KafkaListener(topics = "${topic.minifig}", groupId = "${topic.minifig.group.id}")
    public void onReceive(@Payload Minifig minifig) {
        LOG.info("New minifig received - {}.", minifig);
    }
}
