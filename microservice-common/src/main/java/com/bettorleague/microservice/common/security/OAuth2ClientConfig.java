package com.bettorleague.microservice.common.security;


import feign.RequestInterceptor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.security.oauth2.client.feign.OAuth2FeignRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

@Configuration
@EnableOAuth2Client
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.bettorleague"})
public class OAuth2ClientConfig {

    private final OAuth2ClientContext oauth2ClientContext;

    public OAuth2ClientConfig(OAuth2ClientContext oauth2ClientContext){
        this.oauth2ClientContext = oauth2ClientContext;
    }

    @Bean
    @ConfigurationProperties(prefix = "security.oauth2.client")
    public ClientCredentialsResourceDetails clientCredentialsResourceDetails() {
        return new ClientCredentialsResourceDetails();
    }

    @Bean
    public RequestInterceptor oauth2FeignRequestInterceptor() {
        return new OAuth2FeignRequestInterceptor(oauth2ClientContext, clientCredentialsResourceDetails());
    }

    @Bean
    public OAuth2RestOperations restTemplate() {
        return new OAuth2RestTemplate(clientCredentialsResourceDetails(), oauth2ClientContext);
    }
}
