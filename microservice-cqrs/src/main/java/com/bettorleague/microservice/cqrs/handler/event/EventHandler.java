package com.bettorleague.microservice.cqrs.handler.event;

import com.bettorleague.microservice.cqrs.domain.Event;
import com.bettorleague.microservice.cqrs.handler.Handler;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;

@Slf4j
public class EventHandler extends Handler implements EventHandlerMethod {

    public EventHandler(Object instance, Method method) {
        super(instance, method);
    }

    @Override
    public void on(Event event) {
        invoke(event);
    }
}
