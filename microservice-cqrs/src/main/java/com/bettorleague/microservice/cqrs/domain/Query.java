package com.bettorleague.microservice.cqrs.domain;

public class Query extends Message {
    public Query(){
        super(null);
    }
    public Query(Object payload) {
        super(payload);
    }
}
