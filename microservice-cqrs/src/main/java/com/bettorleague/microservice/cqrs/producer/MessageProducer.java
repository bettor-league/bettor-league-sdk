package com.bettorleague.microservice.cqrs.producer;

import com.bettorleague.microservice.cqrs.domain.Message;

public interface MessageProducer {
    void produce(Message message);
}
