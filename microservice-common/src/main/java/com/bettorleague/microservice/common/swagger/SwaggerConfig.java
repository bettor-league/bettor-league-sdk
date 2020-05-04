package com.bettorleague.microservice.common.swagger;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.OAuthFlow;
import io.swagger.v3.oas.models.security.OAuthFlows;
import io.swagger.v3.oas.models.security.Scopes;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
        @Bean
        public OpenAPI customOpenAPI(@Value("${security.oauth2.client.access-token-uri}") String tokenUrl){
                return  new OpenAPI().components(new Components()
                        .addSecuritySchemes("OAuth2PasswordBearer",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.OAUTH2)
                                        .in(SecurityScheme.In.HEADER).bearerFormat("jwt")
                                        .flows(new OAuthFlows().password(new OAuthFlow().tokenUrl(tokenUrl).scopes(new Scopes().addString("client","ui"))))
                        )
                );

        }
}