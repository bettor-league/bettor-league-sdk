package com.bettorleague.microservice.mongo;


import com.bettorleague.microservice.mongo.config.AuditConfiguration;
import com.bettorleague.microservice.mongo.config.MongoConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = ElementType.TYPE)
@Import({MongoConfig.class, AuditConfiguration.class})
public @interface MongoMicroservice {
}
