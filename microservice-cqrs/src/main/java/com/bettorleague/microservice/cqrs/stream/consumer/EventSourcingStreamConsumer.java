package com.bettorleague.microservice.cqrs.stream.consumer;

import com.bettorleague.microservice.cqrs.domain.Aggregate;
import com.bettorleague.microservice.cqrs.domain.Event;
import com.bettorleague.microservice.cqrs.domain.EventModel;
import com.bettorleague.microservice.cqrs.handler.sourcing.EventSourcingHandler;
import com.bettorleague.microservice.cqrs.repository.EventStoreRepository;
import com.bettorleague.microservice.cqrs.repository.HandlerRepository;
import org.apache.commons.collections4.MapUtils;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public class EventSourcingStreamConsumer extends StreamConsumer<Event, EventSourcingHandler>{
    private final EventStoreRepository eventStoreRepository;

    public EventSourcingStreamConsumer(final Map<Class<?>, EventSourcingHandler> eventSourcingHandlers,
                                       final EventStoreRepository eventStoreRepository) {
        super(eventSourcingHandlers);
        this.eventStoreRepository = eventStoreRepository;
    }

    @Override
    public void apply(String aggregateIdentifier, Event event) {
        if (MapUtils.isEmpty(getHandlerRepository())) {
            return;
        }

        final EventSourcingHandler eventSourcingHandler = getHandler(event);

        if (isNull(eventSourcingHandler)) {
            return;
        }

        final Aggregate aggregate = loadState(aggregateIdentifier);

        final EventModel currentEvent = EventModel.builder()
                .timestamp(Instant.now())
                .aggregateIdentifier(aggregateIdentifier)
                .event(event)
                .build();

        eventStoreRepository.save(currentEvent);

        eventSourcingHandler.apply(aggregate, event);
    }


    private Aggregate loadState(String aggregateIdentifier) {
        Aggregate aggregate = new Aggregate();

        final List<EventModel> events = eventStoreRepository.findByAggregateIdentifier(aggregateIdentifier);

        final AtomicInteger version = new AtomicInteger(0);

        for (EventModel eventModel : events) {
            final Event event = eventModel.getEvent();
            final EventSourcingHandler eventSourcingHandler = getHandler(event);
            if (nonNull(eventSourcingHandler)) {
                aggregate = eventSourcingHandler.apply(aggregate, event);
                version.incrementAndGet();
            } else throw new RuntimeException(String.format("No event sourcing handler for : %s ", event.getTopic()));
        }

        aggregate.setVersion(version.get());

        return aggregate;
    }
}
