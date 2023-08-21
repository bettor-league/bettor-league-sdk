package com.bettorleague.microservice.security.feign;

import com.bettorleague.microservice.security.service.OAuth2ClientServerProvider;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

@Configuration
public class FeignConfig {
    @Bean
    public RequestInterceptor requestInterceptor(final OAuth2ClientServerProvider oAuth2Provider) {
        return requestTemplate -> requestTemplate.header(HttpHeaders.AUTHORIZATION, "Bearer " + oAuth2Provider.getAuthenticationToken());
    }

}
