package com.bettorleague.microservice.cqrs.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;

@Target(value = METHOD)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface HandleCommand {
}
