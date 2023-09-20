package com.bettorleague.microservice.cqrs.handler.query;

import com.bettorleague.microservice.cqrs.domain.Query;
import com.bettorleague.microservice.cqrs.domain.QueryResponse;
import com.bettorleague.microservice.cqrs.handler.Handler;

import java.lang.reflect.Method;

public class QueryHandler extends Handler implements QueryHandlerMethod {

    public QueryHandler(Object instance, Method method) {
        super(instance, method);
    }

    @Override
    public QueryResponse handle(Query query) {
        final Object methodResult = invoke(query);
        return new QueryResponse(methodResult);
    }
}
