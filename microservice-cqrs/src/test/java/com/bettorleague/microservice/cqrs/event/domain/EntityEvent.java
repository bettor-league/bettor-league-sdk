package com.bettorleague.microservice.cqrs.event.domain;

import com.bettorleague.microservice.cqrs.annotations.AggregateIdentifier;
import com.bettorleague.microservice.cqrs.annotations.TopicInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@TopicInfo("events.entity")
public interface EntityEvent {
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    class EntityCreated implements EntityEvent {
        @AggregateIdentifier
        private String aggregateIdentifier;
        private String field;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    class EntityFieldUpdated implements EntityEvent {
        @AggregateIdentifier
        private String aggregateIdentifier;

        private String field;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    class EntityDeleted implements EntityEvent {
        @AggregateIdentifier
        private String aggregateIdentifier;
    }
}
