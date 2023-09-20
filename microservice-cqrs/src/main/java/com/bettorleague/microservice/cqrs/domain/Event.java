package com.bettorleague.microservice.cqrs.domain;


public class Event extends Message {

    public Event(){
        super(null);
    }
    public Event(Object payload) {
        super(payload);
    }
}
