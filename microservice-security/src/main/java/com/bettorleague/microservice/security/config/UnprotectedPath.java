package com.bettorleague.microservice.security.config;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Primary;

import java.util.List;

@Getter
@Setter
@Primary
@ConfigurationProperties(prefix = "security")
public class UnprotectedPath {
    private List<String> unprotectedPath;
}
