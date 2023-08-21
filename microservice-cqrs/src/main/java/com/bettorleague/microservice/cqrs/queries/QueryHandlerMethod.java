package com.bettorleague.microservice.cqrs.queries;

@FunctionalInterface
public interface QueryHandlerMethod<I extends BaseQuery> {
    QueryResponse handle(I query);
}
