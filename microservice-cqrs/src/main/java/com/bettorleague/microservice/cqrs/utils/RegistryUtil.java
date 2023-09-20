package com.bettorleague.microservice.cqrs.utils;

import com.bettorleague.microservice.cqrs.annotations.*;
import com.bettorleague.microservice.cqrs.handler.command.CommandHandler;
import com.bettorleague.microservice.cqrs.handler.event.EventHandler;
import com.bettorleague.microservice.cqrs.handler.query.QueryHandler;
import com.bettorleague.microservice.cqrs.handler.sourcing.EventSourcingHandler;
import com.bettorleague.microservice.cqrs.repository.HandlerRepository;
import com.bettorleague.microservice.cqrs.repository.TopicRepository;
import io.micrometer.common.util.StringUtils;
import lombok.experimental.UtilityClass;
import org.springframework.core.annotation.AnnotationUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

@UtilityClass
public class RegistryUtil {

    public void registerHandler(Object bean, HandlerRepository handlerRepository, TopicRepository topicRepository) {
        final List<Method> commandHandlerMethods = findMethodsWithAnnotation(bean.getClass(), HandleCommand.class);
        final List<Method> eventSourcingMethods = findMethodsWithAnnotation(bean.getClass(), ApplyEvent.class);
        final List<Method> eventHandlerMethods = findMethodsWithAnnotation(bean.getClass(), HandleEvent.class);
        final List<Method> queryHandlerMethods = findMethodsWithAnnotation(bean.getClass(), HandleQuery.class);
        commandHandlerMethods.forEach(method -> addCommandHandler(handlerRepository, topicRepository, bean, method));
        eventSourcingMethods.forEach(method -> addEventSourcingHandler(handlerRepository, topicRepository, bean, method));
        eventHandlerMethods.forEach(method -> addEventHandler(handlerRepository, topicRepository, bean, method));
        queryHandlerMethods.forEach(method -> addQueryHandler(handlerRepository, bean, method));
    }

    private <A extends Annotation> List<Method> findMethodsWithAnnotation(Class<?> c, Class<A> annotation) {
        final List<Method> methods = new ArrayList<>();
        for (final Method method : c.getDeclaredMethods()) {
            final A result = AnnotationUtils.findAnnotation(method, annotation);
            if (nonNull(result)) {
                methods.add(method);
            }
        }
        return methods;
    }

    private Optional<TopicInfo> searchForTopicInfo(Parameter parameter) {
        Optional<TopicInfo> result = Optional.empty();
        if (nonNull(parameter)) {
            final TopicInfo topicInfo = AnnotationUtils.findAnnotation(parameter.getType(), TopicInfo.class);
            if (nonNull(topicInfo) && StringUtils.isNotBlank(topicInfo.value())) {
                result = Optional.of(topicInfo);
            }
        }
        return result;
    }


    private void addTopicInfo(TopicInfo topicInfo, Set<String> registry) {
        if (nonNull(registry) && nonNull(topicInfo) && StringUtils.isNotBlank(topicInfo.value())) {
            registry.add(topicInfo.value());
        }
    }

    private void addCommandHandler(HandlerRepository registry, TopicRepository topicRepository, Object listener, Method method) {
        if (method.getParameterCount() == 1) {
            final Parameter commandAsParameter = method.getParameters()[0];
            final Class<?> commandType = commandAsParameter.getType();
            final String commandName = commandType.getSimpleName();
            registry.getCommandHandlers().put(commandType, new CommandHandler(listener, method));
            //topicRepository.getCommandTopics().add(commandName);
        }
    }

    private void addEventSourcingHandler(HandlerRepository registry, TopicRepository topicRepository, Object listener, Method method) {
        if (method.getParameterCount() == 2) {
            final Parameter aggregateAsParameter = method.getParameters()[0];
            final Parameter eventAsParameter = method.getParameters()[1];
            final Class<?> eventType = eventAsParameter.getType();
            final String eventName = eventType.getSimpleName();
            registry.getEventSourcingHandlers().put(eventType, new EventSourcingHandler(listener, method));
            topicRepository.getEventTopics().add(eventName);
        }
    }

    private void addEventHandler(HandlerRepository registry, TopicRepository topicRepository, Object listener, Method method) {
        if (method.getParameterCount() == 1) {
            final Parameter eventAsParameter = method.getParameters()[0];
            final Class<?> eventType = eventAsParameter.getType();
            final String eventName = eventType.getSimpleName();
            registry.getEventHandlers().put(eventType, new EventHandler(listener, method));
            topicRepository.getEventTopics().add(eventName);
        }
    }

    private void addQueryHandler(HandlerRepository registry, Object listener, Method method) {
        if (method.getParameterCount() == 1) {
            final Parameter queryAsParameter = method.getParameters()[0];
            final Class<?> queryType = queryAsParameter.getType();
            registry.getQueryHandlers().put(queryType, new QueryHandler(listener, method));
        }
    }


    public Set<String> getTopics(Set<Class<?>> handlers) {
        return handlers.stream()
                .map(aClass -> AnnotationUtils.findAnnotation(aClass, TopicInfo.class))
                .filter(Objects::nonNull)
                .map(TopicInfo::value)
                .collect(Collectors.toSet());
    }


}
