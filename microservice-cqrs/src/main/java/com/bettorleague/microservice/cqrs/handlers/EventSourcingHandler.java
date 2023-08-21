package com.bettorleague.microservice.cqrs.handlers;

import com.bettorleague.microservice.cqrs.domain.AggregateRoot;

public interface EventSourcingHandler<T> {
    void save(AggregateRoot aggregate);

    T getById(String id);

    void republishEvents();
}
