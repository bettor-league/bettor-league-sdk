package com.bettorleague.microservice.common.util;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Sort;

import static java.util.Objects.nonNull;

public class Utils {

    public static Sort sort(String field, Sort.Direction direction) {
        Sort response = Sort.unsorted();
        if (StringUtils.isNotBlank(field) && nonNull(direction)) {
            if (Sort.Direction.ASC.equals(direction)) {
                response = Sort.by(Sort.Order.asc(field));
            } else {
                response = Sort.by(Sort.Order.desc(field));
            }
        }
        return response;
    }
}
