package com.hcd.errhandlerdeserializer.listener;

import com.hcd.errhandlerdeserializer.domain.Minifig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.support.serializer.FailedDeserializationInfo;

import java.util.function.Function;

public class MinifigFailedDeserializationFunction implements Function<FailedDeserializationInfo, Minifig> {

    private static final Logger LOG = LoggerFactory.getLogger(MinifigFailedDeserializationFunction.class);

    @Override
    public Minifig apply(FailedDeserializationInfo failedDeserializationInfo) {
        final Exception exception = failedDeserializationInfo.getException();
        LOG.info("Error deserializing minifig - {}", exception.getCause().getMessage());
        return new Minifig("Default");
    }
}
