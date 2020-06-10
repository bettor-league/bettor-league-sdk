package com.bettorleague.microservice.model.exception.registration;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@Data
@ResponseStatus(HttpStatus.CONFLICT)
@EqualsAndHashCode(callSuper = true)
public class CredentialException extends RuntimeException{
    public final String wrongCredential;

    public CredentialException(String wrongCredential, String exception){
        super(exception);
        this.wrongCredential = wrongCredential;
    }
}
