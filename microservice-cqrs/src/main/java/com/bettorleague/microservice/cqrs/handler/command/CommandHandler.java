package com.bettorleague.microservice.cqrs.handler.command;

import com.bettorleague.microservice.cqrs.domain.Command;
import com.bettorleague.microservice.cqrs.domain.Event;
import com.bettorleague.microservice.cqrs.handler.Handler;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
public class CommandHandler extends Handler implements CommandHandlerMethod {

    public CommandHandler(Object instance, Method method) {
        super(instance, method);
    }

    @Override
    public List<Event> handle(Command command) {
        final Object methodResult = invoke(command);

        final List<Object> methodResultList = new ArrayList<>();

        if (List.class.isAssignableFrom(methodResult.getClass())) {
            methodResultList.addAll((List<?>) methodResult);
        } else {
            methodResultList.add(methodResult);
        }

        return methodResultList.stream()
                .filter(Objects::nonNull)
                .map(Event::new)
                .collect(Collectors.toList());
    }

}
