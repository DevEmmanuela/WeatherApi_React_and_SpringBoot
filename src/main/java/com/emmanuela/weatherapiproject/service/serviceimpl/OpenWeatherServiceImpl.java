package com.emmanuela.weatherapiproject.service.serviceimpl;

import com.emmanuela.weatherapiproject.exceptions.GenericException;
import com.emmanuela.weatherapiproject.httpcall.OpenWeatherApiCall;
import com.emmanuela.weatherapiproject.service.OpenWeatherService;
import com.emmanuela.weatherapiproject.util.XmlConversion;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class OpenWeatherServiceImpl implements OpenWeatherService {
    private final OpenWeatherApiCall openWeatherApiCall;
    private final XmlConversion xmlConversion;

    @Override
    public String apiCall(String city) {
        log.info("Running service implementation of api call....");
        String apiCall = openWeatherApiCall.openWeatherApi(city);
        return xmlConversion.convert(apiCall);
    }
}
