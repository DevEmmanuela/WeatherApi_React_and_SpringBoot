package com.emmanuela.weatherapiproject.httpcall;

import com.emmanuela.weatherapiproject.exceptions.CityNotFoundException;
import com.emmanuela.weatherapiproject.exceptions.GenericException;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import java.time.Duration;
import java.util.concurrent.TimeUnit;


@Component
@Slf4j
public class OpenWeatherApiCall {

    @Value("${application.http.callout.api.openweather.baseurl.apikeys}")
    private String openWeatherApiKey;

    public String openWeatherApi(String city){

        log.info("Running webclient ....");

        String formattedUrl = String.format("https://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s", city, openWeatherApiKey);
//        String modifiedUrl = formattedUrl;

        HttpClient httpClient = HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 15000)
                .responseTimeout(Duration.ofSeconds(120))
                .doOnConnected(conn ->
                        conn.addHandlerLast(new ReadTimeoutHandler(120, TimeUnit.SECONDS))
                                .addHandlerLast(new WriteTimeoutHandler(120, TimeUnit.SECONDS)));

        WebClient client = WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();

        Mono<String> responseSpec = client.get()
                .uri(formattedUrl)
                .exchangeToMono(response -> {
                    if (response.statusCode().equals(HttpStatus.OK)) {
                        return response.bodyToMono(String.class);
                    } else if (response.statusCode().is4xxClientError()) {
                        return response.bodyToMono(String.class)
                                .flatMap(errorBody -> Mono.error(new CityNotFoundException(errorBody)));
                    } else {
                        return response.bodyToMono(String.class)
                                .flatMap(errorBody -> Mono.error(new GenericException(errorBody)));
                    }
                });

        String webResponse = responseSpec.block();
        log.info("open weather api web response {}", webResponse);
        return webResponse;
    }
}
