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
        ErrorResponse errorResponse = ErrorResponse.builder()
                .apiErrorMessage(ex.getMessage())
                .localDateTime(LocalDateTime.now())
                .debugMessage("CITY NOT FOUND")
                .httpStatus(HttpStatus.NOT_FOUND)
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(GenericException.class)
    public ResponseEntity<ErrorResponse> handleForGenericException(final GenericException ex) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .apiErrorMessage(ex.getMessage())
                .localDateTime(LocalDateTime.now())
                .debugMessage("INTERNAL SERVER ERROR")
                .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(LengthException.class)
    public ResponseEntity<ErrorResponse> handleForLengthException(final LengthException ex) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .apiErrorMessage(ex.getMessage())
                .localDateTime(LocalDateTime.now())
                .debugMessage("STRING LENGTH MUST BE GREATER OR EQUAL TO ONE")
                .httpStatus(HttpStatus.BAD_REQUEST)
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

}

