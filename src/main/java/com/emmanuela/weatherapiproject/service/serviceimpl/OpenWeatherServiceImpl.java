package com.emmanuela.weatherapiproject.service.serviceimpl;

import com.emmanuela.weatherapiproject.exceptions.LengthException;
import com.emmanuela.weatherapiproject.httpcall.OpenWeatherApiCall;
import com.emmanuela.weatherapiproject.service.OpenWeatherService;
import com.emmanuela.weatherapiproject.util.Util;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class OpenWeatherServiceImpl implements OpenWeatherService {
    private final OpenWeatherApiCall openWeatherApiCall;
    private final Util util;

    @Override
    public String apiCall(String city) throws JsonProcessingException {
        log.info("Running service implementation of api call....");

        String lengthStatus = util.lengthValidation(city);
        if(lengthStatus.equalsIgnoreCase("FALSE")){
            log.info("String length is less than one");
            throw new LengthException("STRING LENGTH MUST BE GREATER OR EQUAL TO ONE");
        }
        String apiCall = openWeatherApiCall.openWeatherApi(city);

        String xmlValue = util.convertToXml(apiCall);
        log.info("xml xmlValue {}", xmlValue);
        return xmlValue;
    }
}
