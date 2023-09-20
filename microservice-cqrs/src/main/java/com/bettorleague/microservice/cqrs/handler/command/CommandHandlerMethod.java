package com.bettorleague.microservice.cqrs.handler.command;

import com.bettorleague.microservice.cqrs.domain.Command;
import com.bettorleague.microservice.cqrs.domain.Event;

import java.util.List;

@FunctionalInterface
public interface CommandHandlerMethod {
    List<Event> handle(Command command);

}
