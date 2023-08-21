package com.bettorleague.microservice.jdbc;

import com.bettorleague.microservice.jdbc.config.AuditConfiguration;
import com.bettorleague.microservice.jdbc.config.JdbcConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = ElementType.TYPE)
@Import({JdbcConfig.class, AuditConfiguration.class})
public @interface JdbcMicroservice {
}
