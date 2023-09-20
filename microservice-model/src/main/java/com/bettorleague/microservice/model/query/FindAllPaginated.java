package com.bettorleague.microservice.model.query;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Pageable;

@Getter
@Setter
@RequiredArgsConstructor
public abstract class FindAllPaginated<T> {
    private final Pageable pageable;
}
