package com.bettorleague.microservice.common.rest;

import com.bettorleague.microservice.model.response.ExceptionResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.micrometer.common.util.StringUtils;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


@Hidden
@RestController
public class GlobalErrorController implements ErrorController {
    final ObjectMapper objectMapper;

    public GlobalErrorController(final ObjectMapper objectMapper){
        this.objectMapper = objectMapper;
    }

    @RequestMapping("/error")
    public ResponseEntity<ExceptionResponse> handleProblem(HttpServletRequest request) {
        final Optional<Throwable> optionalThrowable = Optional.ofNullable(request.getAttribute(RequestDispatcher.ERROR_EXCEPTION))
                .map(Throwable.class::cast);

        final Optional<HttpStatus> optionalHttpStatus = Optional.ofNullable(request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE))
                .map(Integer.class::cast)
                .map(HttpStatus::resolve);

        final Optional<String> optionalMessage = Optional.ofNullable(request.getAttribute(RequestDispatcher.ERROR_MESSAGE))
                .map(String.class::cast)
                .filter(StringUtils::isNotBlank);

        final HttpStatus httpStatus = optionalHttpStatus.orElse(HttpStatus.INTERNAL_SERVER_ERROR);
        final String message = optionalThrowable.map(Throwable::getMessage).or(() -> optionalMessage).orElse(null);

        final ExceptionResponse response = new ExceptionResponse(httpStatus, message);
        return new ResponseEntity<>(response, httpStatus);
    }

}
