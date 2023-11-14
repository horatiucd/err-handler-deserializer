package com.hcd.errhandlerdeserializer.config;

import com.hcd.errhandlerdeserializer.domain.Minifig;
import com.hcd.errhandlerdeserializer.listener.MinifigFailedDeserializationFunction;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class KafkaConfig {

    @Value("${broker.url}")
    private String brokerUrl;

    @Value("${topic.minifig.group.id}")
    private String groupId;

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Minifig> kafkaListenerContainerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, brokerUrl);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.put(JsonDeserializer.TRUSTED_PACKAGES, Minifig.class.getPackageName());
        props.put(JsonDeserializer.VALUE_DEFAULT_TYPE, Minifig.class.getName());

        JsonDeserializer<Minifig> jsonDeserializer = new JsonDeserializer<>(Minifig.class);

        ErrorHandlingDeserializer<Minifig> valueDeserializer = new ErrorHandlingDeserializer<>(jsonDeserializer);
        valueDeserializer.setFailedDeserializationFunction(new MinifigFailedDeserializationFunction());

        DefaultKafkaConsumerFactory<String, Minifig> defaultFactory = new DefaultKafkaConsumerFactory<>(props,
                new StringDeserializer(), valueDeserializer);

        ConcurrentKafkaListenerContainerFactory<String, Minifig> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(defaultFactory);
        factory.setCommonErrorHandler(new DefaultErrorHandler());
        return factory;
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, brokerUrl);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        ProducerFactory<String, String> producerFactory = new DefaultKafkaProducerFactory<>(props);

        return new KafkaTemplate<>(producerFactory);
    }
}
