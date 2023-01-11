package com.emmanuela.weatherapiproject.service;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface OpenWeatherService {
    String apiCall (String city) throws JsonProcessingException;
}
