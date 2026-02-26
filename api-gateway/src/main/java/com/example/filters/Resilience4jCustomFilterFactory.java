package com.example.filters;

import io.github.resilience4j.reactor.retry.RetryOperator;
import io.github.resilience4j.reactor.timelimiter.TimeLimiterOperator;
import io.github.resilience4j.retry.RetryRegistry;
import io.github.resilience4j.timelimiter.TimeLimiterRegistry;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

@Component
public class Resilience4jCustomFilterFactory extends AbstractGatewayFilterFactory<Resilience4jCustomFilterFactory.Config> {

    private final RetryRegistry retryRegistry;
    private final TimeLimiterRegistry timeLimiterRegistry;

    public Resilience4jCustomFilterFactory(RetryRegistry retryRegistry, TimeLimiterRegistry timeLimiterRegistry) {
        super(Config.class);
        this.retryRegistry = retryRegistry;
        this.timeLimiterRegistry = timeLimiterRegistry;
    }

    @Override
    public GatewayFilter apply(Config config) {
        // Mühərrikləri registry-dən ad ilə götürürük
        var retry = retryRegistry.retry(config.getRetryName());
        var timeLimiter = timeLimiterRegistry.timeLimiter(config.getTimeLimiterName());

        return (exchange, chain) ->
                chain.filter(exchange)
                        // 1. Əvvəlcə Timeout (TimeLimiter) yoxlanılır
                        .transformDeferred(TimeLimiterOperator.of(timeLimiter))
                        // 2. Sonra Retry mühərriki xətaları (və timeout-u) izləyir
                        .transformDeferred(RetryOperator.of(retry));
    }

    public static class Config {
        private String retryName;
        private String timeLimiterName;

        public String getRetryName() { return retryName; }
        public void setRetryName(String retryName) { this.retryName = retryName; }
        public String getTimeLimiterName() { return timeLimiterName; }
        public void setTimeLimiterName(String timeLimiterName) { this.timeLimiterName = timeLimiterName; }
    }
}