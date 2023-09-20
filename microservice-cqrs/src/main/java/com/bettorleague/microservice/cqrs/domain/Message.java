package com.bettorleague.microservice.cqrs.domain;

import com.bettorleague.microservice.cqrs.annotations.AggregateIdentifier;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.springframework.util.ReflectionUtils;

import java.beans.Transient;
import java.util.Optional;

@Getter
@Setter
public abstract class Message {

    @JsonProperty("@class")
    private String className;
    @JsonUnwrapped
    private Object payload;
    public Message() {
        this.payload = null;
        this.className = null;
    }

    public Message(Object payload) {
        this.payload = payload;
        this.className = Optional.ofNullable(payload).map(Object::getClass).map(Class::getTypeName).orElse(null);
    }

    @Transient
    public String getAggregateIdentifier() {
        Optional<String> optionalAggregateIdentifier = FieldUtils.getFieldsListWithAnnotation(payload.getClass(), AggregateIdentifier.class)
                .stream()
                .filter(field -> field.getType() == String.class)
                .findFirst()
                .map(field -> {
                    field.setAccessible(true);
                    return (String) ReflectionUtils.getField(field, payload);
                });
        return optionalAggregateIdentifier.orElse(null);
    }

    @Transient
    public String getTopic() {
        return Optional.of(payload)
                .map(Object::getClass)
                .map(Class::getSimpleName)
                .orElse(null);
    }

    @Transient
    public Class<?> getMessageClass() {
        return Optional.ofNullable(payload).map(Object::getClass).orElse(null);
    }

}
