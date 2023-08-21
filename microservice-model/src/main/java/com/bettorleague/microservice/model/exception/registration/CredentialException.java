package com.bettorleague.microservice.model.exception.registration;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class CredentialException extends ResponseStatusException {
    public final String wrongCredential;

    public CredentialException(String wrongCredential, String message) {
        super(HttpStatus.CONFLICT, message);
        this.wrongCredential = wrongCredential;
    }
}
