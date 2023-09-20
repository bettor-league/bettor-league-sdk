package com.bettorleague.microservice.cqrs.config;

import com.bettorleague.microservice.cqrs.repository.HandlerRepository;
import com.bettorleague.microservice.cqrs.repository.TopicRepository;
import com.bettorleague.microservice.cqrs.repository.impl.InMemoryHandlerRepository;
import com.bettorleague.microservice.cqrs.repository.impl.InMemoryTopicRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Slf4j
@Configuration
public class EventMicroserviceConfiguration {

    @Bean
    public HandlerRepository handlerRepository() {
        return new InMemoryHandlerRepository();
    }
    @Bean
    public TopicRepository topicRepository() {
        return new InMemoryTopicRepository();
    }
    @Bean
    public BeanPostProcessor eventBeanPostProcessor(final HandlerRepository handlerRepository,
                                                    final TopicRepository topicRepository) {
        return new BeanPostProcessor(handlerRepository, topicRepository);
    }

}