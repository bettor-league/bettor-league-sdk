package com.bettorleague.microservice.model.exception;

import com.bettorleague.microservice.model.exception.ServiceException;
import org.springframework.http.HttpStatus;

public class ResourceNotFoundException  extends ServiceException {
    public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
        super(HttpStatus.NOT_FOUND, String.format("%s not found with %s :  %s ", resourceName, fieldName, fieldValue));
    }
}