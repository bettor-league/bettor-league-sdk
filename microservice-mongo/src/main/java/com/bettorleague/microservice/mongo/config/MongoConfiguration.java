package com.bettorleague.microservice.mongo.config;

import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import io.mongock.driver.mongodb.springdata.v4.SpringDataMongoV4Driver;
import io.mongock.runner.springboot.MongockSpringboot;
import io.mongock.runner.springboot.base.MongockInitializingBeanRunner;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

import static java.util.Collections.singletonList;

@Configuration
@EnableMongoAuditing
@EnableConfigurationProperties
public class MongoConfiguration {

    private static final String MONGODB_CHANGELOGS_PACKAGE = "com.bettorleague.changelogs";

    @Primary
    @Bean(name = "applicationMongoProperties")
    @ConfigurationProperties(prefix = "mongo.application")
    public MongoProperties mongoProperties() {
        return new MongoProperties();
    }


    @Primary
    @Bean(name = "applicationMongoClient")
    public MongoClient mongoClient(@Qualifier("applicationMongoProperties") MongoProperties mongoProperties) {

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
    @Primary
    @Bean(name = "applicationMongoDBFactory")
    public MongoDatabaseFactory mongoDatabaseFactory(
            @Qualifier("applicationMongoClient") MongoClient mongoClient,
            @Qualifier("applicationMongoProperties") MongoProperties mongoProperties) {
        return new SimpleMongoClientDatabaseFactory(mongoClient, mongoProperties.getDatabase());
    }

    @Primary
    @Bean(name = "mongoTemplate")
    public MongoTemplate mongoTemplate(@Qualifier("applicationMongoDBFactory") MongoDatabaseFactory mongoDatabaseFactory) {
        return new MongoTemplate(mongoDatabaseFactory);
    }

    @Bean
    public SpringDataMongoV4Driver driver(final MongoTemplate mongoTemplate) {
        return SpringDataMongoV4Driver.withDefaultLock(mongoTemplate);
    }

    @Bean
    public MongockInitializingBeanRunner mongockInitializingBeanRunner(final ApplicationContext springContext,
                                                                       final SpringDataMongoV4Driver driver) {
        return MongockSpringboot.builder()
                .setDriver(driver)
                .setSpringContext(springContext)
                .addMigrationScanPackage(MONGODB_CHANGELOGS_PACKAGE)
                .buildInitializingBeanRunner();
    }
}
