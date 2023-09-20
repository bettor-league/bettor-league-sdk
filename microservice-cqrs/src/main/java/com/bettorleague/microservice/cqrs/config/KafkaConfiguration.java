package com.bettorleague.microservice.cqrs.config;

import com.bettorleague.microservice.cqrs.repository.TopicRepository;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.serialization.Serdes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.KafkaStreamsConfiguration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

import static org.apache.kafka.streams.StreamsConfig.*;
import static org.springframework.kafka.annotation.KafkaStreamsDefaultConfiguration.DEFAULT_STREAMS_CONFIG_BEAN_NAME;


@Configuration
public class KafkaConfiguration {

    @Value("${spring.application.name}")
    private String applicationName;

    @Value("${spring.kafka.bootstrap-servers}")
    private String boostrapServer;


    @Bean(name = DEFAULT_STREAMS_CONFIG_BEAN_NAME)
    public KafkaStreamsConfiguration kafkaStreamsConfig() {
        Map<String, Object> props = new HashMap<>();
        props.put(APPLICATION_ID_CONFIG, applicationName);
        props.put(BOOTSTRAP_SERVERS_CONFIG, boostrapServer);
        props.put(DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        return new KafkaStreamsConfiguration(props);
    }


    @Bean
    public KafkaAdmin kafkaAdmin(final TopicRepository topicRepository) {
        final Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, boostrapServer);
        final KafkaAdmin kafkaAdmin = new KafkaAdmin(configs);
        kafkaAdmin.createOrModifyTopics();
        topicRepository.getAllTopics().forEach(topicName -> {
            final NewTopic newTopic = TopicBuilder.name(topicName).build();
            kafkaAdmin.createOrModifyTopics(newTopic);
        });
        return kafkaAdmin;
    }

}
