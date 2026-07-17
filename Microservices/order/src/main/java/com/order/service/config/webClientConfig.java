package com.order.service.config;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class webClientConfig {
    @Bean
    @LoadBalanced
    public WebClient.Builder webClientBuilder(){
        return WebClient.builder();
    }
    @Bean
    public WebClient productWebClient(){
        return webClientBuilder().baseUrl("http://product/api/v1/products").build();
    }

    @Bean
    public WebClient inventoryWebClient(){
        return webClientBuilder().baseUrl("http://inventory/api/v1/inventories").build();
    }
}
