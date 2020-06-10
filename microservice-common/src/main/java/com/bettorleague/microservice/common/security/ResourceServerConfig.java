package com.bettorleague.microservice.common.security;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;


@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Value("${security.enabled:true}")
    private Boolean securityEnabled;

    @Value("${security.social:false}")
    private Boolean socialEnabled;

    private final List<String> unprotectedPath;

    public ResourceServerConfig(UnprotectedPath unprotectedPath){
        this.unprotectedPath = Optional.ofNullable(unprotectedPath).map(UnprotectedPath::getUnprotectedPath).orElseGet(ArrayList::new);
    }


    @Override
    public void configure(HttpSecurity http) throws Exception {

        for(String path: unprotectedPath){
            http.authorizeRequests().antMatchers(path).permitAll();
        }

        if(securityEnabled){
            http.authorizeRequests().anyRequest().authenticated();
        }else{
            http.authorizeRequests().anyRequest().permitAll();
        }

        if(socialEnabled){
            http.oauth2Login()
                    .authorizationEndpoint()
                    .baseUri("/oauth2/authorize")
                    .and()
                    .redirectionEndpoint()
                    .baseUri("/oauth2/callback/*");
        }

        http
                .exceptionHandling()
                .authenticationEntryPoint((request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED))
                .and()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .headers().frameOptions().disable();
    }
}
