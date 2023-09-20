package com.bettorleague.microservice.cqrs.handler.sourcing;

import com.bettorleague.microservice.cqrs.domain.Aggregate;
import com.bettorleague.microservice.cqrs.domain.Event;
import com.bettorleague.microservice.cqrs.handler.Handler;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.util.Optional;

@Slf4j
public class EventSourcingHandler extends Handler implements EventSourcingHandlerMethod {

    public EventSourcingHandler(Object instance, Method method) {
        super(instance, method);
    }

    @Override
    public Aggregate apply(Aggregate aggregate, Event event) {
        final Object methodResult = invoke(aggregate, event);
        return wrapResult(methodResult);
    }

    private Aggregate wrapResult(Object result) {
        return Optional.ofNullable(result)
                .map(Aggregate::new)
                .orElse(null);
    }
}
