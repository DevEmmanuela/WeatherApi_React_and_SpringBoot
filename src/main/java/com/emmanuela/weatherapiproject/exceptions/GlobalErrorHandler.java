package com.emmanuela.weatherapiproject.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleForCityNotFoundException(final CityNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setApiErrorMessage(ex.getMessage());
        errorResponse.setLocalDateTime(LocalDateTime.now());
        errorResponse.setDebugMessage("CITY NOT FOUND");
        errorResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(GenericException.class)
    public ResponseEntity<ErrorResponse> handleForGenericException(final GenericException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setApiErrorMessage(ex.getMessage());
        errorResponse.setLocalDateTime(LocalDateTime.now());
        errorResponse.setDebugMessage("INTERNAL SERVER ERROR");
        errorResponse.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}

