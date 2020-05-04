package com.bettorleague.microservice.mongo.config;

import com.github.cloudyrock.mongock.SpringBootMongock;
import com.github.cloudyrock.mongock.SpringBootMongockBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = {"com.bettorleague"})
public class MongoConfig {

    private static final String MONGODB_CHANGELOGS_PACKAGE = "com.bettorleague.changelogs";
    private final MongoTemplate mongoTemplate;

    public MongoConfig(MongoTemplate mongoTemplate){
        this.mongoTemplate = mongoTemplate;
    }

    @Bean
    public SpringBootMongock mongock(ApplicationContext springContext) {
        return new SpringBootMongockBuilder(mongoTemplate, MONGODB_CHANGELOGS_PACKAGE)
                .setApplicationContext(springContext)
                .setLockQuickConfig()
                .build();
    }
}
