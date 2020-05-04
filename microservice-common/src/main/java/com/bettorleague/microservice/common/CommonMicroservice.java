package com.bettorleague.microservice.common;

import com.bettorleague.microservice.common.security.MethodSecurityConfig;
import com.bettorleague.microservice.common.security.OAuth2ClientConfiguration;
import com.bettorleague.microservice.common.security.ResourceServerConfig;
import com.bettorleague.microservice.common.swagger.SwaggerConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = ElementType.TYPE)
@Import({
        SwaggerConfig.class,
        ResourceServerConfig.class,
        OAuth2ClientConfiguration.class,
        MethodSecurityConfig.class,
})
public @interface CommonMicroservice{
}
