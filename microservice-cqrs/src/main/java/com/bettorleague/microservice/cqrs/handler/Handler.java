package com.bettorleague.microservice.cqrs.handler;

import com.bettorleague.microservice.cqrs.domain.Message;
import jakarta.validation.ValidationException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.function.Consumer;

@Getter
@Slf4j
@RequiredArgsConstructor
public abstract class Handler {
    private final Object instanceHandler;
    private final Method methodHandler;
    public Object invoke(Message... messages) {
        final Object methodResult;
        try {
            methodResult = switch (messages.length) {
                case 1 -> methodHandler.invoke(instanceHandler, messages[0].getPayload());
                case 2 -> methodHandler.invoke(instanceHandler, messages[0].getPayload(), messages[1].getPayload());
                default -> null;
            };
        } catch (Exception e) {
            final Throwable throwable = ExceptionUtils.getRootCause(e);
            final String exceptionMessage = ExceptionUtils.getRootCauseMessage(e);

            if (throwable instanceof ValidationException) {
                log.error("Handling failed: ", throwable);
            } else {
                log.error("Handling failed: ", throwable);
            }

            throw new RuntimeException(exceptionMessage, throwable);
        }

        return methodResult;

    }


}
