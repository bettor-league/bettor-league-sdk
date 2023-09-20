package com.bettorleague.microservice.cqrs.stream.consumer;

import com.bettorleague.microservice.cqrs.domain.Message;
import org.apache.kafka.streams.kstream.ForeachAction;

public interface MessageConsumer<T extends Message> extends ForeachAction<String, T> {

}
