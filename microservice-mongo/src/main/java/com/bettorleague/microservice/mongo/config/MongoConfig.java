package com.bettorleague.microservice.mongo.config;

import com.github.cloudyrock.mongock.SpringBootMongock;
import com.github.cloudyrock.mongock.SpringBootMongockBuilder;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoAuditing
@EnableMongoRepositories(basePackages = {"com.bettorleague"})
@EnableAutoConfiguration(exclude = { EmbeddedMongoAutoConfiguration.class })
public class MongoConfig {

    private static final String MONGODB_CHANGELOGS_PACKAGE = "com.bettorleague.changelogs";
    private final MongoTemplate mongoTemplate;

    public MongoConfig(MongoTemplate mongoTemplate){
        this.mongoTemplate = mongoTemplate;
    }

    @Bean
    public SpringBootMongock mongock(ApplicationContext springContext,
                                     Environment environment) {
        return new SpringBootMongockBuilder(mongoTemplate, MONGODB_CHANGELOGS_PACKAGE)
                .setApplicationContext(springContext)
                .setSpringEnvironment(environment)
                .setLockQuickConfig()
                .build();
    }
}
