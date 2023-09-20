package com.bettorleague.microservice.cqrs.dispatacher;

import com.bettorleague.microservice.cqrs.domain.QueryResponse;

public interface QueryDispatcher {
    QueryResponse send(Object query);
}
