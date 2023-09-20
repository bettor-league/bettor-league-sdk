package com.bettorleague.microservice.cqrs.repository.impl;

import com.bettorleague.microservice.cqrs.handler.command.CommandHandler;
import com.bettorleague.microservice.cqrs.handler.event.EventHandler;
import com.bettorleague.microservice.cqrs.handler.query.QueryHandler;
import com.bettorleague.microservice.cqrs.handler.sourcing.EventSourcingHandler;
import com.bettorleague.microservice.cqrs.repository.HandlerRepository;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
public class InMemoryHandlerRepository implements HandlerRepository {

    private final Map<Class<?>, CommandHandler> commandHandlers = new HashMap<>();
    private final Map<Class<?>, EventSourcingHandler> eventSourcingHandlers = new HashMap<>();
    private final Map<Class<?>, EventHandler> eventHandlers = new HashMap<>();
    private final Map<Class<?>, QueryHandler> queryHandlers = new HashMap<>();

    @Override
    public Map<Class<?>, CommandHandler> getCommandHandlers() {
        return commandHandlers;
    }

    @Override
    public Map<Class<?>, EventSourcingHandler> getEventSourcingHandlers() {
        return eventSourcingHandlers;
    }

    @Override
    public Map<Class<?>, EventHandler> getEventHandlers() {
        return eventHandlers;
    }

    @Override
    public Map<Class<?>, QueryHandler> getQueryHandlers() {
        return queryHandlers;
    }
}
