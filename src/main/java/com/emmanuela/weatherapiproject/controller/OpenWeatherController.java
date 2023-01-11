package com.emmanuela.weatherapiproject.controller;

import com.emmanuela.weatherapiproject.service.OpenWeatherService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class OpenWeatherController {
    private final OpenWeatherService openWeatherService;
    @GetMapping(value = "/city/{city}", produces = "text/xml")
    public String getApiResponseByCity(@PathVariable String city) throws JsonProcessingException {
        return openWeatherService.apiCall(city);
    }
}
