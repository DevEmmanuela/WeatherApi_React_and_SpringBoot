package com.emmanuela.weatherapiproject.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Setter
public class ErrorResponse {
    private String apiErrorMessage;
    private String debugMessage;
    private HttpStatus httpStatus;
    private LocalDateTime localDateTime = LocalDateTime.now();

}

