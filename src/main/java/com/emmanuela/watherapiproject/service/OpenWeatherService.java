package com.emmanuela.watherapiproject.service;

import com.emmanuela.watherapiproject.exceptions.EndpointException;

public interface OpenWeatherService {

    String apiCall (String city) throws EndpointException;
}
