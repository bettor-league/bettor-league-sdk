package com.bettorleague.microservice.cqrs.annotations;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.lang.annotation.*;


@Inherited
@JacksonAnnotationsInside
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property = "@class")
public @interface Aggregate {
}
