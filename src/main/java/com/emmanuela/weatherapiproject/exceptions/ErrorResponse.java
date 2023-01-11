package com.emmanuela.weatherapiproject.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorResponse {
    private String apiErrorMessage;
    private String debugMessage;
    private HttpStatus httpStatus;
    private LocalDateTime localDateTime = LocalDateTime.now();

}

