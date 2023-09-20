package com.bettorleague.microservice.cqrs.config;

import com.bettorleague.microservice.cqrs.dispatacher.CommandDispatcher;
import com.bettorleague.microservice.cqrs.dispatacher.CommandDispatcherImpl;
import com.bettorleague.microservice.cqrs.dispatacher.QueryDispatcher;
import com.bettorleague.microservice.cqrs.dispatacher.QueryDispatcherImpl;
import com.bettorleague.microservice.cqrs.json.JsonSerializer;
import com.bettorleague.microservice.cqrs.producer.MessageProducer;
import com.bettorleague.microservice.cqrs.producer.MessageProducerImpl;
import com.bettorleague.microservice.cqrs.repository.HandlerRepository;
import com.bettorleague.microservice.cqrs.utils.PropertyUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.Properties;

@Configuration
public class DispatcherConfiguration {

    @Value("${spring.kafka.bootstrap-servers}")
    private String boostrapServer;

    @Bean
    public KafkaTemplate<String, Object> kafkaTemplate(final ProducerFactory<String, Object> producerFactory) {
        return new KafkaTemplate<>(producerFactory);
    }

    @Bean("producerProperties")
    public Properties producerProperties() {
        final Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, boostrapServer);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return properties;
    }

    @Bean
    public ProducerFactory<String, Object> producerFactory(final Properties producerProperties,
                                                           final ObjectMapper objectMapper) {
        final DefaultKafkaProducerFactory<String, Object> producerFactory = new DefaultKafkaProducerFactory<>(PropertyUtils.convert(producerProperties));
        producerFactory.setKeySerializer(new StringSerializer());
        producerFactory.setValueSerializer(new JsonSerializer<>(Object.class, objectMapper));
        return producerFactory;
    }

    @Bean
    public MessageProducer messageProducer(final KafkaTemplate<String, Object> kafkaTemplate) {
        return new MessageProducerImpl(kafkaTemplate);
    }

    @Bean
    public CommandDispatcher commandDispatcher(final HandlerRepository handlerRepository, final MessageProducer messageProducer) {
        return new CommandDispatcherImpl(handlerRepository.getCommandHandlers(), messageProducer);
    }

    @Bean
    public QueryDispatcher queryDispatcher(final HandlerRepository handlerRepository) {
        return new QueryDispatcherImpl(handlerRepository.getQueryHandlers());
    }
}
