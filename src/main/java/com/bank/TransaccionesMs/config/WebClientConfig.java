package com.bank.TransaccionesMs.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;


@Configuration
public class WebClientConfig {

    @Value("${accountms.url}")
    private String accountMsUrl;

    @Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }

    @Bean
    public WebClient cuentaWebClient(WebClient.Builder webClientBuilder) {
        return webClientBuilder
                .baseUrl(accountMsUrl)
                .build();
    }
}