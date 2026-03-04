package com.example.service;

import com.example.dto.InventoryRequest;
import com.example.dto.InventoryResponse;
import com.example.kafkaEvent.InventoryCreatedEvent;
import com.example.mapper.InventoryMapper;
import com.example.model.Inventory;
import com.example.repository.InventoryRepository;
import com.example.service.producer.InventoryEventProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;
    private  final InventoryMapper inventoryMapper;
    private final InventoryEventProducer inventoryEventProducer;

    public InventoryResponse addInventory(InventoryRequest inventoryRequest) {
        Inventory inventory = inventoryMapper.toEntity(inventoryRequest);
        Inventory savedInventory = inventoryRepository.save(inventory);
        // Publish event to Kafka
        InventoryCreatedEvent event = new InventoryCreatedEvent(
                savedInventory.getId(),
                savedInventory.getProductCode(),
                savedInventory.getAvailableQuantity()
        );
        inventoryEventProducer.sendInventoryCreatedEvent(event);
        return inventoryMapper.toResponse(savedInventory);
    }
}
