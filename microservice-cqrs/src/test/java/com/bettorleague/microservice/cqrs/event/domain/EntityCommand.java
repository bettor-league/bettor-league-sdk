package com.bettorleague.microservice.cqrs.event.domain;

import com.bettorleague.microservice.cqrs.annotations.AggregateIdentifier;
import com.bettorleague.microservice.cqrs.annotations.TopicInfo;
import lombok.*;

@TopicInfo("commands.entity")
public interface EntityCommand {

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    class CreateEntity implements EntityCommand {
        @AggregateIdentifier
        private String aggregateIdentifier;
        private String field;
    }


    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    class UpdateEntityField implements EntityCommand {
        @AggregateIdentifier
        private String aggregateIdentifier;

        private String field;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    class DeleteEntity implements EntityCommand {
        @AggregateIdentifier
        private String aggregateIdentifier;
    }
}
