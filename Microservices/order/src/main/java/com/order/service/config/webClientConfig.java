package com.order.service.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class webClientConfig {
    @Bean
    public WebClient inventoryWebClient(){
        return WebClient.builder().baseUrl("http://localhost:8082/api/v1/inventories").build();
    }
    @Bean
    public WebClient productWebClient(){
        return WebClient.builder().baseUrl("http://localhost:8083/api/v1/products").build();
    }
}
