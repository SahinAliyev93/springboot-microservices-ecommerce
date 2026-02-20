package com.example.controller;

import com.example.dto.InventoryRequest;
import com.example.dto.InventoryResponse;
import com.example.service.InventoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/inventory")
@RequiredArgsConstructor
public class InventoryController {
    private final InventoryService inventoryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    public InventoryResponse addInventory(@Valid @RequestBody InventoryRequest request) {
        return inventoryService.addInventory(request);
    }

    @PostMapping("/debug-auth")
    public String debugAuthorities(Authentication authentication) {
        return authentication.getAuthorities().toString();
    }
}
