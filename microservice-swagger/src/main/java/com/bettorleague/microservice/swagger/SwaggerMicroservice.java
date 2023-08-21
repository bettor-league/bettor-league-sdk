package com.bettorleague.microservice.swagger;

import com.bettorleague.microservice.swagger.config.SwaggerConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = ElementType.TYPE)
@Import({
        SwaggerConfig.class,
})
public @interface SwaggerMicroservice {
}
