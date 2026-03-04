package com.example.service.consumer;

import com.example.kafkaEvent.OrderCreatedEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class OrderEventConsumer {

    @KafkaListener(
            topics = "order-created-topic",
            groupId = "payment-group"
    )
    public void handleOrderCreatedEvent(OrderCreatedEvent event) {
        System.out.printf("PaymentService received order: %d%n", event.getOrderId());
    }
}