package com.bettorleague.microservice.common.security;

import feign.FeignException;
import org.json.JSONObject;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;


@RestControllerAdvice
@ComponentScan("com.bettorleague")
public class GlobalExceptionHandler {

    @ExceptionHandler(FeignException.class)
    public Map<String,Object> handleFeignStatusException(FeignException exception, HttpServletResponse response) {
        response.setStatus(exception.status());
        return new JSONObject(exception.contentUTF8()).toMap();
    }

}
