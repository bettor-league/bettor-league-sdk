package com.bettorleague.microservice.cqrs.stream.consumer;

import com.bettorleague.microservice.cqrs.domain.Message;
import com.bettorleague.microservice.cqrs.handler.Handler;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@Getter
@RequiredArgsConstructor
public abstract class StreamConsumer <M extends Message, H extends Handler> implements MessageConsumer<M> {
    private final Map<Class<?>, H> handlerRepository;

    public H getHandler(Message message){
        final Class<?> messageClass = message.getMessageClass();
        return this.handlerRepository.get(messageClass);
    }
}
