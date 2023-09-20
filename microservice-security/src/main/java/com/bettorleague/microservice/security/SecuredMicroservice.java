package com.bettorleague.microservice.security;

import com.bettorleague.microservice.common.cors.CorsConfig;
import com.bettorleague.microservice.security.config.ResourceServerConfig;
import com.bettorleague.microservice.security.config.UnprotectedPath;
import com.bettorleague.microservice.security.feign.FeignConfig;
import com.bettorleague.microservice.security.service.OAuth2ClientServerProvider;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = ElementType.TYPE)
@EnableConfigurationProperties(UnprotectedPath.class)
@Import(value = {
        CorsConfig.class,
        OAuth2ClientServerProvider.class,
        ResourceServerConfig.class,
        FeignConfig.class,
        UnprotectedPath.class
})
public @interface SecuredMicroservice {

}
