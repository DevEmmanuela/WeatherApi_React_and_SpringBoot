package com.emmanuela.weatherapiproject.exceptions;

public class EndpointUnreachableException extends RuntimeException{
    public EndpointUnreachableException(String message) {
        super(message);
    }
}
