package com.bettorleague.microservice.cqrs.repository.impl;

import com.bettorleague.microservice.cqrs.repository.TopicRepository;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;


@NoArgsConstructor
public class InMemoryTopicRepository implements TopicRepository {

    final Set<String> eventTopics = new HashSet<>();
    final Set<String> commandTopics = new HashSet<>();

    @Override
    public Set<String> getEventTopics() {
        return eventTopics;
    }

    @Override
    public Set<String> getCommandTopics() {
        return commandTopics;
    }

    @Override
    public Set<String> getAllTopics() {
        final Set<String> result = new HashSet<>();
        result.addAll(eventTopics);
        result.addAll(commandTopics);
        return result;
    }
}
