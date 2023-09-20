package com.bettorleague.microservice.common.rest;

import com.bettorleague.microservice.model.exception.ServiceException;
import com.bettorleague.microservice.model.response.ExceptionResponse;
import com.bettorleague.microservice.model.validator.ValidationError;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;


@RestControllerAdvice
@ComponentScan("com.bettorleague")
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleValidationException(MethodArgumentNotValidException ex) {
        final List<ValidationError> errors = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.add(new ValidationError(fieldName, errorMessage));
        });
        final HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        final ExceptionResponse response = new ExceptionResponse(httpStatus, "Validation error", errors);
        return new ResponseEntity<>(response, httpStatus);
    }

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<ExceptionResponse> handleServiceException(ServiceException exception) {
        final HttpStatus httpStatus = Optional.ofNullable(exception).map(ServiceException::getHttpStatus).orElse(HttpStatus.INTERNAL_SERVER_ERROR);
        final Optional<String> throwableMessage = Optional.ofNullable(exception).map(Throwable::getMessage);
        final Optional<String> exceptionMessage = Optional.ofNullable(exception).map(ServiceException::getMessage);
        final String message = Stream.of(throwableMessage, exceptionMessage).filter(Optional::isPresent).map(Optional::get).findFirst().orElse(null);
        final ExceptionResponse response = new ExceptionResponse(httpStatus, message);
        return new ResponseEntity<>(response, httpStatus);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ExceptionResponse> handleResponseStatusException(ResponseStatusException exception) {
        final HttpStatus httpStatus = Optional.ofNullable(exception).map(ResponseStatusException::getStatusCode)
                .map(HttpStatusCode::value)
                .map(HttpStatus::resolve)
                .orElse(HttpStatus.INTERNAL_SERVER_ERROR);
        final String reason = Optional.ofNullable(exception).map(ResponseStatusException::getReason)
                .orElse(null);
        final ExceptionResponse response = new ExceptionResponse(httpStatus, reason);
        return new ResponseEntity<>(response, httpStatus);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleValidationException(Exception exception) {
        final Optional<ServiceException> optionalServiceException = asServiceException(exception);
        if (optionalServiceException.isPresent()) {
            return handleServiceException(optionalServiceException.get());
        }
        final HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        final ExceptionResponse response = new ExceptionResponse(httpStatus, exception.getLocalizedMessage());
        return new ResponseEntity<>(response, httpStatus);
    }

    private Optional<ServiceException> asServiceException(Exception exception) {
        Optional<ServiceException> result = Optional.empty();
        final Throwable cause = exception.getCause();
        if (ServiceException.class.isAssignableFrom(cause.getClass())) {
            result = Optional.of((ServiceException) cause);
        }
        return result;
    }


}
