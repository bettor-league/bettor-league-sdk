package com.bettorleague.microservice.cqrs.events;

import com.bettorleague.microservice.cqrs.messages.Message;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseEvent extends Message {
    private int version;
}
