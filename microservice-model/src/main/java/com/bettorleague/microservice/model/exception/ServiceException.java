package com.bettorleague.microservice.model.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Getter
public class ServiceException extends ResponseStatusException {
    private final String message;
    private final HttpStatus httpStatus;
    public ServiceException(HttpStatus httpStatus, String message) {
        super(httpStatus, message);
        this.httpStatus = httpStatus;
        this.message = message;
    }
}
