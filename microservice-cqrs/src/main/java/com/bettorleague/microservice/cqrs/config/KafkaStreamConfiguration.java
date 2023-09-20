package com.bettorleague.microservice.cqrs.config;

import com.bettorleague.microservice.cqrs.domain.Event;
import com.bettorleague.microservice.cqrs.handler.event.EventHandler;
import com.bettorleague.microservice.cqrs.handler.sourcing.EventSourcingHandler;
import com.bettorleague.microservice.cqrs.json.JsonSerde;
import com.bettorleague.microservice.cqrs.repository.EventStoreRepository;
import com.bettorleague.microservice.cqrs.repository.HandlerRepository;
import com.bettorleague.microservice.cqrs.repository.TopicRepository;
import com.bettorleague.microservice.cqrs.stream.consumer.EventSourcingStreamConsumer;
import com.bettorleague.microservice.cqrs.stream.consumer.EventStreamConsumer;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.KafkaStreamsConfiguration;

import java.util.Map;
import java.util.Set;

import static java.util.Objects.nonNull;

@Slf4j
@Configuration
public class KafkaStreamConfiguration {
    @Bean
    public KafkaStreams kafkaStreams(final Topology topology,
                                     final KafkaStreamsConfiguration kafkaStreamsConfiguration) {
        KafkaStreams kafkaStreams = new KafkaStreams(topology, kafkaStreamsConfiguration.asProperties());
        kafkaStreams.start();
        return kafkaStreams;
    }

    @Bean
    public Topology topology(final ObjectMapper objectMapper,
                             final HandlerRepository handlerRepository,
                             final TopicRepository topicRepository,
                             final EventStoreRepository eventStoreRepository) {
        final StreamsBuilder builder = new StreamsBuilder();
        final Serde<Event> eventSerde = new JsonSerde<>(Event.class, objectMapper);
        final Set<String> eventTopics = topicRepository.getEventTopics();

        KStream<String, Event> eventStream = null;

        if (CollectionUtils.isNotEmpty(eventTopics)) {
            eventStream = builder.stream(eventTopics, Consumed.with(Serdes.String(), eventSerde))
                    .filter((aggregateIdentifier, event) -> nonNull(aggregateIdentifier))
                    .filter((aggregateIdentifier, event) -> nonNull(event))
                    .filter((aggregateIdentifier, event) -> nonNull(event.getPayload()));
        }

        final Map<Class<?>, EventSourcingHandler> eventSourcingHandlers = handlerRepository.getEventSourcingHandlers();

        if (nonNull(eventStream) && MapUtils.isNotEmpty(eventSourcingHandlers)) {
            final EventSourcingStreamConsumer eventSourcingStreamConsumer = new EventSourcingStreamConsumer(eventSourcingHandlers, eventStoreRepository);
            eventStream.peek(eventSourcingStreamConsumer);

        }

        final Map<Class<?>, EventHandler> eventHandler = handlerRepository.getEventHandlers();

        if (nonNull(eventStream) && MapUtils.isNotEmpty(eventHandler)) {
            final EventStreamConsumer eventStreamConsumer = new EventStreamConsumer(eventHandler);
            eventStream.peek(eventStreamConsumer);
        }

        return builder.build();
    }
}
