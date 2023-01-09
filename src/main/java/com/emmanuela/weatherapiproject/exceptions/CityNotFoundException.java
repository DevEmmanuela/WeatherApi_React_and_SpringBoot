package com.emmanuela.weatherapiproject.exceptions;

public class CityNotFoundException extends Exception{
    public CityNotFoundException(String message) {
        super(message);
    }
}
