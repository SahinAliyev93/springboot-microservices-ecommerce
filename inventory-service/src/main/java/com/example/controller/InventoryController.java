package com.example.controller;

import com.example.dto.InventoryRequest;
import com.example.dto.InventoryResponse;
import com.example.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/inventory")
@RequiredArgsConstructor
public class InventoryController {
    private final InventoryService inventoryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InventoryResponse addInventory(@RequestBody InventoryRequest request) {
        return inventoryService.addInventory(request);
    }
}
