package com.bettorleague.microservice.cqrs.stream.consumer;

import com.bettorleague.microservice.cqrs.domain.Event;
import com.bettorleague.microservice.cqrs.handler.event.EventHandler;

import java.util.Map;

import static java.util.Objects.nonNull;

public class EventStreamConsumer extends StreamConsumer<Event, EventHandler> {

    public EventStreamConsumer(final Map<Class<?>, EventHandler> eventHandlers) {
        super(eventHandlers);
    }

    @Override
    public void apply(String aggregateIdentifier, Event event) {
        final EventHandler eventHandler = getHandler(event);
        if (nonNull(eventHandler)) {
            eventHandler.on(event);
        }
    }
}
