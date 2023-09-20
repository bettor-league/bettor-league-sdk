package com.bettorleague.microservice.cqrs.stream.consumer;

import com.bettorleague.microservice.cqrs.domain.Message;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.KeyValueMapper;

import java.lang.reflect.InvocationTargetException;

public class MessageMapper<VR extends Message> implements KeyValueMapper<String, Object, KeyValue<String, VR>> {
    private final Class<VR> clazz;

    public MessageMapper(Class<VR> clazz) {
        this.clazz = clazz;
    }

    @Override
    public KeyValue<String, VR> apply(String key, Object object) {
        try {
            final VR instance = clazz.getDeclaredConstructor(Object.class).newInstance(object);
            return new KeyValue<>(key, instance);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}
