package com.bettorleague.microservice.cqrs.domain;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Getter
@Builder
@Document(collection = "eventStore")
public class EventModel {
    @Id
    private String id;
    private Instant timestamp;
    private String aggregateIdentifier;
    private Event event;

}
