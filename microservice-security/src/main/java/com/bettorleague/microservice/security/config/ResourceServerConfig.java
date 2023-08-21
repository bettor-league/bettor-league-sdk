package com.bettorleague.microservice.security.config;


import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.client.AuthorizedClientServiceOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



@Configuration
@EnableWebSecurity
public class ResourceServerConfig {

    @Value("${security.enabled:true}")
    private Boolean securityEnabled;

    private final List<String> unprotectedPath;

    public ResourceServerConfig(final UnprotectedPath unprotectedPath) {
        this.unprotectedPath = Optional.ofNullable(unprotectedPath).map(UnprotectedPath::getUnprotectedPath).orElseGet(ArrayList::new);
    }

    @Bean
    public OAuth2AuthorizedClientManager authorizedClientManager(final ClientRegistrationRepository clientRegistrationRepository,
                                                                 final OAuth2AuthorizedClientService authorizedClientService) {
        return new AuthorizedClientServiceOAuth2AuthorizedClientManager(clientRegistrationRepository, authorizedClientService);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(final HttpSecurity http,
                                                   final CorsConfigurationSource corsConfigurationSource) throws Exception {

        for (String path : unprotectedPath) {
            http.authorizeHttpRequests().requestMatchers(path).permitAll();
        }

        if (securityEnabled) {
            http.authorizeHttpRequests().anyRequest().authenticated();
        } else {
            http.authorizeHttpRequests().anyRequest().permitAll();
        }

        http
                .exceptionHandling()
                .authenticationEntryPoint((request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED))
                .and()
                .cors().configurationSource(corsConfigurationSource)
                .and()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .headers().frameOptions().disable()
                .and()
                .oauth2Client()
                .and()
                .oauth2ResourceServer()
                .jwt();

        return http.build();
    }
}
