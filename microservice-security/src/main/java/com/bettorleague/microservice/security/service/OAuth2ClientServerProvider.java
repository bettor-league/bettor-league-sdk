package com.bettorleague.microservice.security.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.core.AbstractOAuth2Token;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

@Component
public class OAuth2ClientServerProvider {
    private static final String CLIENT_SERVER_NAME = "server-client";
    private final Authentication principal;
    private final OAuth2AuthorizedClientManager authorizedClientManager;

    public OAuth2ClientServerProvider(final OAuth2AuthorizedClientManager authorizedClientManager) {
        this.authorizedClientManager = authorizedClientManager;
        this.principal = createPrincipal();
    }

    public String getAuthenticationToken() {
        final OAuth2AuthorizeRequest request = OAuth2AuthorizeRequest
                .withClientRegistrationId(CLIENT_SERVER_NAME)
                .principal(principal)
                .build();
        final OAuth2AuthorizedClient client = authorizedClientManager.authorize(request);
        return Optional.ofNullable(client)
                .map(OAuth2AuthorizedClient::getAccessToken)
                .map(AbstractOAuth2Token::getTokenValue)
                .orElseThrow(() -> new IllegalStateException("Client credentials flow on " + CLIENT_SERVER_NAME + " failed, client is null"));
    }

    private Authentication createPrincipal() {
        return new Authentication() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return Collections.emptySet();
            }

            @Override
            public Object getCredentials() {
                return null;
            }

            @Override
            public Object getDetails() {
                return null;
            }

            @Override
            public Object getPrincipal() {
                return this;
            }

            @Override
            public boolean isAuthenticated() {
                return false;
            }

            @Override
            public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
            }

            @Override
            public String getName() {
                return CLIENT_SERVER_NAME;
            }
        };
    }
}