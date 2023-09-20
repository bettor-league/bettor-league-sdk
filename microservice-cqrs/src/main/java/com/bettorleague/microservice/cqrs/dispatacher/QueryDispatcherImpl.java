package com.bettorleague.microservice.cqrs.dispatacher;

import com.bettorleague.microservice.cqrs.domain.Query;
import com.bettorleague.microservice.cqrs.domain.QueryResponse;
import com.bettorleague.microservice.cqrs.handler.query.QueryHandler;

import java.util.Map;

import static java.util.Objects.isNull;

public class QueryDispatcherImpl extends Dispatcher<QueryHandler> implements QueryDispatcher{

    public QueryDispatcherImpl(Map<Class<?>, QueryHandler> handlers) {
        super(handlers);
    }

    @Override
    public QueryResponse send(Object queryRequest) {
        final QueryHandler queryHandler = getHandler(queryRequest);

        if (isNull(queryHandler)) {
            throw new RuntimeException(String.format("No query handler was registered for %s", queryRequest.getClass().getSimpleName()));
        }

        final Query query = new Query(queryRequest);
        return queryHandler.handle(query);
    }

}
