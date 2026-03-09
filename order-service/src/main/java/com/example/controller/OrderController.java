package com.example.controller;

import com.example.dto.OrderRequest;
import com.example.dto.OrderResponse;
import com.example.service.OrderService;
import com.example.service.inventory.InventoryServiceFeign;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final InventoryServiceFeign inventoryServiceFeign;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderResponse create(@RequestBody OrderRequest request) {
        return orderService.createOrder(request);
    }


    @GetMapping("/test")
    public String test() {
       throw new RuntimeException("Error");
    }

    @GetMapping("/testInventory")
    public String testInventory() {
        return inventoryServiceFeign.getTest();
    }
}
