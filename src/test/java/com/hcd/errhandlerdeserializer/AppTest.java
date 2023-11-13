package com.hcd.errhandlerdeserializer;

import com.hcd.errhandlerdeserializer.domain.Minifig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.UUID;

@SpringBootTest
class AppTest {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value("${topic.minifig}")
    private String topic;

    final String template = "{" +
            "\"id\":\"%s\"," +
            "\"size\":\"%s\"," +
            "\"name\":\"%s\"" +
            "}";

    @Test
    void send_compliant() {
        final String minifig = String.format(template,
                UUID.randomUUID(), Minifig.Size.SMALL, "Spider-Man");

        kafkaTemplate.send(topic, minifig);
    }

    @Test
    void send_non_compliant() {
        final String minifig = String.format(template,
                UUID.randomUUID(), "Unknown Size", "Spider-Man");

        kafkaTemplate.send(topic, minifig);
    }
}
