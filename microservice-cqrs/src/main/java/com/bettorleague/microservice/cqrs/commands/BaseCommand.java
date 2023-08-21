package com.bettorleague.microservice.cqrs.commands;

import com.bettorleague.microservice.cqrs.messages.Message;
import lombok.*;
import lombok.experimental.SuperBuilder;


@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class BaseCommand extends Message {
}
