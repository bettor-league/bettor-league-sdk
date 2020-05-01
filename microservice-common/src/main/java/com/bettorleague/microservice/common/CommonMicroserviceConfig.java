package com.bettorleague.microservice.common;

import com.bettorleague.microservice.common.security.MethodSecurityConfig;
import com.bettorleague.microservice.common.security.OAuth2ClientConfiguration;
import com.bettorleague.microservice.common.security.ResourceServerConfig;
import com.bettorleague.microservice.common.swagger.SwaggerConfig;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@EnableFeignClients
@EnableDiscoveryClient
@EnableConfigurationProperties
@Import({
        SwaggerConfig.class,
        ResourceServerConfig.class,
        OAuth2ClientConfiguration.class,
        MethodSecurityConfig.class,
})
public class CommonMicroserviceConfig {
}
