package com.bettorleague.microservice.cqrs.event.handler.sourcing;

import com.bettorleague.microservice.cqrs.annotations.ApplyEvent;
import com.bettorleague.microservice.cqrs.event.domain.EntityAggregate;
import com.bettorleague.microservice.cqrs.event.domain.EntityEvent;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class EntitySourcingHandler {

    @ApplyEvent
    public EntityAggregate handle(EntityAggregate state, EntityEvent.EntityCreated event) {
        return EntityAggregate.builder()
                .aggregateIdentifier(event.getAggregateIdentifier())
                .dateCreated(Instant.now())
                .build();
    }

    @ApplyEvent
    public EntityAggregate handle(EntityAggregate state, EntityEvent.EntityFieldUpdated event) {
        return state.toBuilder()
                .field(event.getField())
                .build();
    }


    @ApplyEvent
    public EntityAggregate handle(EntityAggregate state, EntityEvent.EntityDeleted event) {
        return null;
    }
}
