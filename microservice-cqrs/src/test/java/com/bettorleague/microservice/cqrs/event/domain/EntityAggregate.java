package com.bettorleague.microservice.cqrs.event.domain;

import com.bettorleague.microservice.cqrs.annotations.Aggregate;
import com.bettorleague.microservice.cqrs.annotations.AggregateIdentifier;
import lombok.Builder;

import java.time.Instant;


@Aggregate
@Builder(toBuilder = true)
public class EntityAggregate {
    @AggregateIdentifier
    private String aggregateIdentifier;
    private String field;
    private Instant dateCreated;
}
