package com.bettorleague.microservice.cqrs.dispatacher;

import com.bettorleague.microservice.cqrs.handler.Handler;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.MapUtils;

import java.util.Map;

import static java.util.Objects.nonNull;

@RequiredArgsConstructor
public abstract class Dispatcher<T extends Handler>  {
    private final Map<Class<?>, T> handlers;

    public T getHandler(Object request) {
        if (nonNull(request) && MapUtils.isNotEmpty(handlers)) {
            final Class<?> queryRequestClass = request.getClass();
            return this.handlers.get(queryRequestClass);
        } else return null;
    }
}
