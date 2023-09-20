package com.bettorleague.microservice.cqrs.event.handler.event;

import com.bettorleague.microservice.cqrs.annotations.HandleEvent;
import com.bettorleague.microservice.cqrs.event.domain.EntityEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EntityEventHandler {

    @HandleEvent
    public void on(EntityEvent.EntityCreated event) {
        log.info(event.toString());
    }

    @HandleEvent
    public void on(EntityEvent.EntityFieldUpdated event) {
    }

    @HandleEvent
    public void on(EntityEvent.EntityDeleted event) {
    }

}
