package com.bettorleague.microservice.cqrs.producer;

import com.bettorleague.microservice.cqrs.domain.Message;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;


@RequiredArgsConstructor
public class MessageProducerImpl implements MessageProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public void produce(Message message) {
        final ProducerRecord<String, Object> record = new ProducerRecord<>(message.getTopic(), message.getAggregateIdentifier(), message);
        this.kafkaTemplate.send(record);
    }
}
