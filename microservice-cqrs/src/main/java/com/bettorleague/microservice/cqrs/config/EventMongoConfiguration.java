package com.bettorleague.microservice.cqrs.config;

import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import static java.util.Collections.singletonList;

@Configuration
@EnableConfigurationProperties
@EnableMongoRepositories(
        basePackages = "com.bettorleague.microservice.cqrs.repository",
        mongoTemplateRef = "eventMongoTemplate"
)
public class EventMongoConfiguration {

    @Bean(name = "eventMongoProperties")
    @ConfigurationProperties(prefix = "mongo.event")
    public MongoProperties secondaryProperties() {
        return new MongoProperties();
    }


    @Bean(name = "eventMongoClient")
    public MongoClient mongoClient(@Qualifier("eventMongoProperties") MongoProperties mongoProperties) {

        final MongoCredential credential = MongoCredential
                .createCredential(
                        mongoProperties.getUsername(),
                        mongoProperties.getAuthenticationDatabase(),
                        mongoProperties.getPassword()
                );

        return MongoClients.create(MongoClientSettings.builder()
                .applyToClusterSettings(builder -> builder
                        .hosts(singletonList(new ServerAddress(mongoProperties.getHost(), mongoProperties.getPort()))))
                .credential(credential)
                .build());
    }
    @Bean(name = "eventMongoDBFactory")
    public MongoDatabaseFactory mongoDatabaseFactory(
            @Qualifier("eventMongoClient") MongoClient mongoClient,
            @Qualifier("eventMongoProperties") MongoProperties mongoProperties) {
        return new SimpleMongoClientDatabaseFactory(mongoClient, mongoProperties.getDatabase());
    }

    @Bean(name = "eventMongoTemplate")
    public MongoTemplate mongoTemplate(@Qualifier("eventMongoDBFactory") MongoDatabaseFactory mongoDatabaseFactory) {
        return new MongoTemplate(mongoDatabaseFactory);
    }
}
