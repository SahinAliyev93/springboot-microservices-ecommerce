package com.example.service.inventory;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "inventory-service",configuration = FeignSecurityConfig.class)
public interface InventoryServiceFeignClient {

    @GetMapping("/api/v1/inventory/test")
    String getTest();
}
