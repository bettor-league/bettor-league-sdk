package com.bettorleague.microservice.cqrs.producers;

import com.bettorleague.microservice.cqrs.events.BaseEvent;

public interface EventProducer {
    void produce(String topic, BaseEvent event);
}
