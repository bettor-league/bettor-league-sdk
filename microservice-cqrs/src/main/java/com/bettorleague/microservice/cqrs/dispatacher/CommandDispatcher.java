package com.bettorleague.microservice.cqrs.dispatacher;

public interface CommandDispatcher {
    void send(Object command);

}
