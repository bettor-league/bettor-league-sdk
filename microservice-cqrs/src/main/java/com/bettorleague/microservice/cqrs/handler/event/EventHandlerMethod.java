
package com.bettorleague.microservice.cqrs.handler.event;

import com.bettorleague.microservice.cqrs.domain.Event;
@FunctionalInterface
public interface EventHandlerMethod {
    void on(Event event);

}
