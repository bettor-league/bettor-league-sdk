package com.bettorleague.microservice.cqrs.event.handler.command;


import com.bettorleague.microservice.cqrs.annotations.HandleCommand;
import com.bettorleague.microservice.cqrs.event.domain.EntityCommand;
import com.bettorleague.microservice.cqrs.event.domain.EntityEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EntityCommandHandler {

    @HandleCommand
    public EntityEvent handle(final EntityCommand.CreateEntity command) {
        return EntityEvent.EntityCreated.builder()
                .aggregateIdentifier(command.getAggregateIdentifier())
                .field(command.getField())
                .build();
    }

    @HandleCommand
    public EntityEvent handle(final EntityCommand.UpdateEntityField command) {
        return EntityEvent.EntityFieldUpdated.builder()
                .aggregateIdentifier(command.getAggregateIdentifier())
                .field(command.getField())
                .build();
    }

    @HandleCommand
    public EntityEvent handle(final EntityCommand.DeleteEntity command) {
        return EntityEvent.EntityDeleted.builder()
                .aggregateIdentifier(command.getAggregateIdentifier())
                .build();
    }
}
