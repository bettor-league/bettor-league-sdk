package com.bettorleague.microservice.swagger.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.OAuthFlow;
import io.swagger.v3.oas.models.security.OAuthFlows;
import io.swagger.v3.oas.models.security.Scopes;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.Map;

import static org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication.Type.REACTIVE;
import static org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication.Type.SERVLET;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI(@Value("${springdoc.oauth-flow.authorization-url}") final String authorizationUrl,
                                 @Value("${springdoc.oauth-flow.token-url}") final String tokenUrl) {
        return new OpenAPI().components(
                new Components()
                        .addSecuritySchemes("OAuth2TokenBearer",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.OAUTH2)
                                        .extensions(Map.of("x-tokenName", "id_token"))
                                        .flows(new OAuthFlows().authorizationCode(
                                                        new OAuthFlow()
                                                                .authorizationUrl(authorizationUrl)
                                                                .tokenUrl(tokenUrl)
                                                                .scopes(new Scopes().addString("openid", "write only"))
                                                )
                                        )
                        )
        );

    }
}