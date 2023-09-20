package com.bettorleague.microservice.cqrs.repository;

import java.util.Set;

public interface TopicRepository {
    Set<String> getEventTopics();
    Set<String> getCommandTopics();
    Set<String> getAllTopics();
}
