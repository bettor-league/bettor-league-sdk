package com.bettorleague.microservice.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExceptionResponse {
    private LocalDateTime timestamp;
    private Integer status;
    private String error;
    private String message;
    private Object details;

    public ExceptionResponse(HttpStatus httpStatus, String message, Object details) {
        this.status = httpStatus.value();
        this.error = httpStatus.getReasonPhrase();
        this.message = message;
        this.timestamp = LocalDateTime.now();
        this.details = details;
    }

    public ExceptionResponse(HttpStatus httpStatus, String message) {
        this.status = httpStatus.value();
        this.error = httpStatus.getReasonPhrase();
        this.message = message;
        this.timestamp = LocalDateTime.now();
        this.details = null;
    }

}
