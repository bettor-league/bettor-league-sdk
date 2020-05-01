package com.bettorleague.microservice.common.swagger;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.OAuthFlow;
import io.swagger.v3.oas.annotations.security.OAuthFlows;
import io.swagger.v3.oas.annotations.security.OAuthScope;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@SecurityScheme(
        name = "OAuth2PasswordBearer",
        type = SecuritySchemeType.OAUTH2,
        in = SecuritySchemeIn.HEADER,
        bearerFormat = "jwt",
        flows = @OAuthFlows(
                password = @OAuthFlow(
                        tokenUrl = "http://localhost:4000/api/authentication-service/oauth/token",
                        scopes = @OAuthScope(name = "ui"))
        )
)
public class SwaggerConfig {
}
