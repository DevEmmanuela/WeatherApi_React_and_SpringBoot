package com.emmanuela.watherapiproject.controller;


import com.emmanuela.watherapiproject.exceptions.EndpointException;
import com.emmanuela.watherapiproject.service.OpenWeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OpenWeatherController {
    private final OpenWeatherService openWeatherService;
    @GetMapping("/city/{city}")
    public String getApiResponseByCity(@PathVariable String city) throws EndpointException {
        return openWeatherService.apiCall(city);
    }
}
