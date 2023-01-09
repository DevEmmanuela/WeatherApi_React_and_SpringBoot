package com.emmanuela.weatherapiproject.service.serviceimpl;

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
    private String apiCall;
    private String convertToXml;

    @Override
    public String apiCall(String city){
        apiCall = openWeatherApiCall.openWeatherApi(city);
        convertToXml = xmlConversion.convert(apiCall);
        return convertToXml;
    }
}
