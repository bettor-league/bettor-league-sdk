package com.bettorleague.microservice.mongo.config;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.util.Optional;

@Component
public class AuditConfiguration implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        final SecurityContext securityContext = SecurityContextHolder.getContext();
        final Optional<String> optionalUserName = Optional.of(securityContext).map(SecurityContext::getAuthentication).map(Principal::getName);
        return optionalUserName.or(() -> Optional.of("server"));
    }
}
