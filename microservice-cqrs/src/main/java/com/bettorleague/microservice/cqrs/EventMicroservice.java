package com.bettorleague.microservice.cqrs;

import com.bettorleague.microservice.cqrs.config.EventMicroserviceConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = ElementType.TYPE)
@Import({EventMicroserviceConfiguration.class})
public @interface EventMicroservice {
}
