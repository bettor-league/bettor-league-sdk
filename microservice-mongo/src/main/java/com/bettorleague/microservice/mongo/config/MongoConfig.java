package com.bettorleague.microservice.mongo.config;

import io.mongock.driver.mongodb.springdata.v4.SpringDataMongoV4Driver;
import io.mongock.runner.springboot.MongockSpringboot;
import io.mongock.runner.springboot.base.MongockInitializingBeanRunner;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoAuditing
@EnableMongoRepositories(basePackages = {"com.bettorleague"})
@EnableAutoConfiguration
public class MongoConfig {

    private static final String MONGODB_CHANGELOGS_PACKAGE = "com.bettorleague.changelogs";
    private final MongoTemplate mongoTemplate;

    public MongoConfig(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Bean
    public SpringDataMongoV4Driver driver() {
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
