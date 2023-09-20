package com.bettorleague.microservice.cqrs.domain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Aggregate extends Message {
    private int version;
    public Aggregate(){
        super(null);
        this.version = 0;
    }
    public Aggregate(Object payload) {
        super(payload);
    }
}
