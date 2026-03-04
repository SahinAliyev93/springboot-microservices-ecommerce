package com.example.service.producer;

import com.example.kafkaEvent.InventoryCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryEventProducer {
    private static final String INVENTORY_CREATED_TOPIC = "inventory-created-topic";
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendInventoryCreatedEvent(InventoryCreatedEvent event) {
        kafkaTemplate.send(INVENTORY_CREATED_TOPIC, event);
    }
}
