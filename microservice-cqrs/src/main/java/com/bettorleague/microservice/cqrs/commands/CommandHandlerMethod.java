package com.bettorleague.microservice.cqrs.commands;

@FunctionalInterface
public interface CommandHandlerMethod<T extends BaseCommand> {
    void handle(T command);
}
