package com.bettorleague.microservice.cqrs.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = {"com.bettorleague.microservice.cqrs.repository"})
public class EventMicroserviceConfiguration {
}
