package com.bettorleague.microservice.cqrs.json;

import com.bettorleague.microservice.cqrs.domain.Message;
import com.bettorleague.microservice.cqrs.utils.JsonUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.ByteArrayInputStream;
import java.util.Map;

public class JsonDeserializer<T extends Message> implements Deserializer<T> {

    private final Class<T> targetType;
    private final ObjectMapper objectMapper;
    final ObjectReader reader;

    public JsonDeserializer(Class<T> targetType, ObjectMapper objectMapper) {
        this.targetType = targetType;
        this.objectMapper = objectMapper;
        reader = objectMapper.reader();
    }

    @Override
    public void configure(Map<String, ?> props, boolean isKey) {
    }

    @Override
    public T deserialize(String topic, byte[] bytes) {
        if (bytes == null) {
            return null;
        }
        try {
            final JsonNode jsonNode = reader.readTree(new ByteArrayInputStream(bytes));
            final Object object = JsonUtils.toJavaType(jsonNode, objectMapper);
            return targetType.getDeclaredConstructor(Object.class).newInstance(object);
        } catch (Exception e) {
            throw new SerializationException("Error deserializing JSON", ExceptionUtils.getRootCause(e));
        }
    }

    @Override
    public void close() {
    }

}