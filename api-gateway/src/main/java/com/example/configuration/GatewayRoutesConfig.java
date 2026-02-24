package com.example.configuration;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Configuration
public class GatewayRoutesConfig {

    @Bean
    public RouteLocator customRoutes(RouteLocatorBuilder builder) {
        return builder.routes()

                .route("order-service-route", r -> r
                        .path("/api/v1/orders/**")
                        .filters(f -> f
                                .addRequestHeader("X-Gateway", "ApiGateway")
                        )
                        .uri("lb://order-service")
                )

                .route("payment-service-route", r -> r
                        .path("/api/v1/payments/**")
                        .filters(f -> f
                                .addRequestHeader("X-Gateway", "ApiGateway")
                        )
                        .uri("lb://payment-service")
                )

                .route("inventory-service-route", r -> r
                        .path("/api/v1/inventory/**")
                        .filters(f -> f
                                .addRequestHeader("X-Gateway", "ApiGateway")
                        )
                        .uri("lb://inventory-service")
                )

                .build();
    }


}
