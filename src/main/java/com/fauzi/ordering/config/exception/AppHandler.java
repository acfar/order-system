package com.fauzi.ordering.config.exception;

import com.fauzi.ordering.model.response.ErrorResponse;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
@Log4j2
public class AppHandler {
    private static final String MESSAGE = "{} message {}";

    @ExceptionHandler(BusinessException.class)
    private ResponseEntity<Object> handleBusinessException(BusinessException exception) {
        log.error(
                MESSAGE, exception.getClass().getSimpleName(), ExceptionUtils.getStackTrace(exception));
        return new ResponseEntity<>(
                buildResponse(exception.getStatus(), exception.getMessage()), exception.getHttpStatus());
    }

    @ExceptionHandler(Exception.class)
    private ResponseEntity<Object> handleGeneralException(Exception exception) {
        log.error(
                MESSAGE, exception.getClass().getSimpleName(), ExceptionUtils.getStackTrace(exception));
        return new ResponseEntity<>(
                buildResponse("Failed", "Something went wrong in our server, try again later"), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<String> errors = new ArrayList<>();
        ex.getBindingResult()
                .getAllErrors()
                .forEach(error -> errors.add((error.getDefaultMessage())));
        return new ResponseEntity<>(
                buildResponse("Failed", errors.get(0)), HttpStatus.BAD_REQUEST);

    }

    private ErrorResponse buildResponse(String status, String message) {
        return ErrorResponse.builder()
                .status(status)
                .message(message)
                .build();
    }
}
