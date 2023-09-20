package com.bettorleague.microservice.model.query;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public abstract class FindByIdentifier<T> {
    private final String identifier;
}
