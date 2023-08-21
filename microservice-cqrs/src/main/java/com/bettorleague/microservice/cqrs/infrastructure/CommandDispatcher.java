package com.bettorleague.microservice.cqrs.infrastructure;

import com.bettorleague.microservice.cqrs.commands.BaseCommand;
import com.bettorleague.microservice.cqrs.commands.CommandHandlerMethod;

public interface CommandDispatcher {

    <T extends BaseCommand> void registerHandler(Class<T> type, CommandHandlerMethod<T> handler);

    void send(BaseCommand command);
}
