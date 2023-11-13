package com.hcd.errhandlerdeserializer.service;

import com.hcd.errhandlerdeserializer.domain.MinifigRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
class MinifigProducerTest {

    @Autowired
    private MinifigProducer producer;

    @Test
    void send() {
        final MinifigRequest request = new MinifigRequest();
        request.setId(UUID.randomUUID().toString());
        request.setName("Spider-Man");
        request.setType(MinifigRequest.Type.GOOD);

        producer.send(request);

        Assertions.assertNotNull(request);
    }
}
