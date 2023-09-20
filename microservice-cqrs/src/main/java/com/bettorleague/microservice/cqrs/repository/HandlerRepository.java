package com.bettorleague.microservice.cqrs.repository;

import com.bettorleague.microservice.cqrs.handler.command.CommandHandler;
import com.bettorleague.microservice.cqrs.handler.event.EventHandler;
import com.bettorleague.microservice.cqrs.handler.query.QueryHandler;
import com.bettorleague.microservice.cqrs.handler.sourcing.EventSourcingHandler;

import java.util.Map;

public interface HandlerRepository {
    Map<Class<?>, CommandHandler> getCommandHandlers();

    Map<Class<?>, EventSourcingHandler> getEventSourcingHandlers();

    Map<Class<?>, EventHandler> getEventHandlers();

    Map<Class<?>, QueryHandler> getQueryHandlers();
}
