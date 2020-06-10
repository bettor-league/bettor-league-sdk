package com.bettorleague.microservice.common;

import com.bettorleague.microservice.common.security.*;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = ElementType.TYPE)
@Import({
        OAuth2ClientConfig.class,
        MethodSecurityConfig.class,
        UnprotectedPath.class,
        ObjectMapperConfig.class,
        GlobalExceptionHandler.class
})
public @interface AuthorizationServerMicroservice {
}
