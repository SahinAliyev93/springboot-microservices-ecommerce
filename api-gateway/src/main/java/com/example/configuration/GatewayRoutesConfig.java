package com.example.configuration;

import com.example.filters.Resilience4jCustomFilterFactory;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.time.Duration;
import java.util.Set;

@Configuration
public class GatewayRoutesConfig {

    private final Resilience4jCustomFilterFactory retryFilterFactory;

    public GatewayRoutesConfig(Resilience4jCustomFilterFactory retryFilterFactory) {
        this.retryFilterFactory = retryFilterFactory;
    }

    @Bean
    public RouteLocator customRoutes(RouteLocatorBuilder builder) {
        return builder.routes()

                .route("order-service-route", r -> r
                        .path("/api/v1/orders/**")
                        .filters(f -> f
                                .addRequestHeader("X-Gateway", "ApiGateway")
                                .filter(retryFilterFactory.apply(config -> {
                                    config.setRetryName("orderServiceRetry");
                                    config.setTimeLimiterName("orderServiceTimeLimiter");
                                }))
                                .circuitBreaker(c -> c
                                        .setName("orderServiceCircuitBreaker")
                                        .setFallbackUri("forward:/fallback/order")
                                        .setStatusCodes(Set.of("500", "502", "503", "504")))

                        )
                        .uri("lb://order-service")
                )

                .route("payment-service-route", r -> r
                        .path("/api/v1/payments/**")
                        .filters(f -> f
                                .addRequestHeader("X-Gateway", "ApiGateway")
                                .filter(retryFilterFactory.apply(config ->{
                                    config.setRetryName("paymentServiceRetry");
                                    config.setTimeLimiterName("paymentServiceTimeLimiter");
                                }))
                                .circuitBreaker(c -> c
                                        .setName("paymentServiceCircuitBreaker")
                                        .setFallbackUri("forward:/fallback/payment"))
                        )
                        .uri("lb://payment-service")
                )

                .route("inventory-service-route", r -> r
                        .path("/api/v1/inventory/**")
                        .filters(f -> f
                                .addRequestHeader("X-Gateway", "ApiGateway")
                                .filter(retryFilterFactory.apply(config -> {
                                    config.setRetryName("inventoryServiceRetry");
                                    config.setTimeLimiterName("inventoryServiceTimeLimiter");
                                }))
                                .circuitBreaker(c -> c
                                        .setName("inventoryServiceCircuitBreaker")
                                        .setFallbackUri("forward:/fallback/inventory"))
                        )
                        .uri("lb://inventory-service")
                )

                .build();
    }


}
