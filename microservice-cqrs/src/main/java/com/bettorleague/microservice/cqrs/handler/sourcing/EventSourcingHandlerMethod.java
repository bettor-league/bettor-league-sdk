package com.bettorleague.microservice.cqrs.handler.sourcing;

import com.bettorleague.microservice.cqrs.domain.Aggregate;
import com.bettorleague.microservice.cqrs.domain.Event;

@FunctionalInterface
public interface EventSourcingHandlerMethod {
    Aggregate apply(Aggregate aggregate, Event event);
}
