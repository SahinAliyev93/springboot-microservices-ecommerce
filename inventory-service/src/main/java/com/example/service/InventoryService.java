package com.example.service;

import com.example.dto.InventoryRequest;
import com.example.dto.InventoryResponse;
import com.example.mapper.InventoryMapper;
import com.example.model.Inventory;
import com.example.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;
    private  final InventoryMapper inventoryMapper;

    public InventoryResponse addInventory(InventoryRequest inventoryRequest) {
        Inventory inventory = inventoryMapper.toEntity(inventoryRequest);
        Inventory savedInventory = inventoryRepository.save(inventory);
        return inventoryMapper.toResponse(savedInventory);
    }
}
