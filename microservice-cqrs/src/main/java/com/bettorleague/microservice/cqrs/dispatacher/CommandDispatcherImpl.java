package com.bettorleague.microservice.cqrs.dispatacher;

import com.bettorleague.microservice.cqrs.domain.Command;
import com.bettorleague.microservice.cqrs.domain.Event;
import com.bettorleague.microservice.cqrs.handler.command.CommandHandler;
import com.bettorleague.microservice.cqrs.handler.command.CommandHandlerMethod;
import com.bettorleague.microservice.cqrs.producer.MessageProducer;

import java.util.List;
import java.util.Map;

import static java.util.Objects.isNull;


public class CommandDispatcherImpl extends Dispatcher<CommandHandler> implements CommandDispatcher {
    private final MessageProducer messageProducer;

    public CommandDispatcherImpl(Map<Class<?>, CommandHandler> handlers, MessageProducer messageProducer) {
        super(handlers);
        this.messageProducer = messageProducer;
    }

    @Override
    public void send(Object commandRequest) {
        final CommandHandlerMethod handler = getHandler(commandRequest);

        final Command command = new Command(commandRequest);

        if (isNull(handler)) {
            throw new RuntimeException(String.format("No command handler was registered for %s", commandRequest.getClass().getSimpleName()));
        }

        final List<Event> events = handler.handle(command);

        for (Event event : events) {
            messageProducer.produce(event);
        }

    }
}
