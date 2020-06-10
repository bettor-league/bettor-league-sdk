package com.bettorleague.microservice.model.exception.registration;

public class UsernameException extends CredentialException {
    public UsernameException(String username) {
        super(username,String.format("A user already exists with this username: %s",username));
    }
}
