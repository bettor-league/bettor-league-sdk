package com.bettorleague.microservice.common;

import com.bettorleague.microservice.common.cors.CorsConfig;
import com.bettorleague.microservice.common.jackson.ObjectMapperConfig;
import com.bettorleague.microservice.common.rest.GlobalErrorController;
import com.bettorleague.microservice.common.rest.GlobalExceptionHandler;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = ElementType.TYPE)
@Import(value = {
        CorsConfig.class,
        ObjectMapperConfig.class,
        GlobalExceptionHandler.class,
        GlobalErrorController.class
})
public @interface CommonMicroservice {

}
