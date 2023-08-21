package com.bettorleague.microservice.model.exception.registration;


public class EmailException extends CredentialException {
    public EmailException(String email) {
        super(email, String.format("A user already exists with this email : %s", email));
    }
}
