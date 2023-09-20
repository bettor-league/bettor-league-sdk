package com.bettorleague.microservice.model.exception.registration;

import com.bettorleague.microservice.model.exception.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class CredentialException extends ServiceException {
    public final String wrongCredential;

    public CredentialException(String wrongCredential, String message) {
        super(HttpStatus.CONFLICT, message);
        this.wrongCredential = wrongCredential;
    }
}
