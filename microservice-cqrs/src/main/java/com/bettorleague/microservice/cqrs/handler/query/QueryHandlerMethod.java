package com.bettorleague.microservice.cqrs.handler.query;

import com.bettorleague.microservice.cqrs.domain.Query;
import com.bettorleague.microservice.cqrs.domain.QueryResponse;

@FunctionalInterface
public interface QueryHandlerMethod {
    QueryResponse handle(Query query);

}
