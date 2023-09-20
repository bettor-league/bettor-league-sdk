package com.bettorleague.microservice.model.doc;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public abstract class Page<T> extends PageImpl<T> {
    public Page(List<T> content, Pageable pageable, long total) {
        super(content, pageable, total);
    }
}
