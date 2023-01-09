package com.emmanuela.watherapiproject.httpcall;

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

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.concurrent.TimeUnit;


@Component
@Slf4j
public class OpenWeatherApiCall {

    @Value("${application.http.callout.api.openweather.baseurl.apikeys}")
    private String baseUrl;
    private String modifiedWeatherUrl;

    public String openWeatherApi(String city){

        log.info("Running webclient ....");

        try{

            String encodeUrl = URLEncoder.encode(city, StandardCharsets.UTF_8.name());
            modifiedWeatherUrl = baseUrl + encodeUrl;
        }
        catch(UnsupportedEncodingException ex){
            log.error("Encoding Error Encountered {}", ex.getMessage());
        }

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
                .uri(modifiedWeatherUrl)
                .exchangeToMono(response -> {
                    if (response.statusCode().equals(HttpStatus.OK)) {
                        return response.bodyToMono(String.class);
                    } else if (response.statusCode().is4xxClientError()) {
                        return Mono.just("CITY NOT FOUND");
                    } else {
                        return response.createException().flatMap(Mono::error);
                    }
                });

        String webResponse = responseSpec.block();
        log.info("open weather api web response {}", webResponse);
        return webResponse;
    }
}
