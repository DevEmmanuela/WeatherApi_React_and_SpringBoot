package com.emmanuela.weatherapiproject.service;

import com.emmanuela.weatherapiproject.exceptions.EndpointException;

public interface OpenWeatherService {

    String apiCall (String city) throws EndpointException;
}
