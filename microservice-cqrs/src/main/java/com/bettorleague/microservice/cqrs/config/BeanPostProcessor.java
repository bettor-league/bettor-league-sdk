package com.bettorleague.microservice.cqrs.config;

import com.bettorleague.microservice.cqrs.repository.HandlerRepository;
import com.bettorleague.microservice.cqrs.repository.TopicRepository;
import com.bettorleague.microservice.cqrs.utils.RegistryUtil;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeansException;
import org.springframework.core.PriorityOrdered;

@RequiredArgsConstructor
public class BeanPostProcessor implements org.springframework.beans.factory.config.BeanPostProcessor, PriorityOrdered {
    private final HandlerRepository handlerRepository;

    private final TopicRepository topicRepository;

    @Override
    public Object postProcessBeforeInitialization(@NonNull Object bean, @NonNull String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(@NonNull Object bean, @NonNull String beanName) throws BeansException {
        RegistryUtil.registerHandler(bean, handlerRepository, topicRepository);
        return bean;
    }

    @Override
    public int getOrder() {
        return PriorityOrdered.HIGHEST_PRECEDENCE;
    }
}
