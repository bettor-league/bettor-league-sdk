package com.bettorleague.microservice.cqrs.infrastructure;

import com.bettorleague.microservice.cqrs.queries.BaseQuery;
import com.bettorleague.microservice.cqrs.queries.QueryHandlerMethod;
import com.bettorleague.microservice.cqrs.queries.QueryResponse;

public interface QueryDispatcher {
    <T extends BaseQuery> void registerHandler(Class<T> type, QueryHandlerMethod<T> handler);

    QueryResponse send(BaseQuery query);
}
