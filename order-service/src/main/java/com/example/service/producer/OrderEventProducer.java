package com.example.service.producer;

import com.example.kafkaEvent.OrderCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderEventProducer {
        private static final String ORDER_CREATED_TOPIC = "order-created-topic";
        private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendOrderCreatedEvent(OrderCreatedEvent event) {
        kafkaTemplate.send(ORDER_CREATED_TOPIC, event);
    }
}
