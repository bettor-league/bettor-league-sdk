package com.bettorleague.microservice.cqrs.domain;


public class Command extends Message {
    public Command(){
        super(null);
    }
    public Command(Object payload) {
        super(payload);
    }
}
