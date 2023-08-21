package com.bettorleague.microservice.cqrs.infrastructure;

import com.bettorleague.microservice.cqrs.events.BaseEvent;

import java.util.List;

public interface EventStore {

    void save(String aggregateId, Iterable<BaseEvent> events, int expectedVersion);

    List<BaseEvent> getEvents(String aggregateId);

    List<String> getAggregateIds();
}
