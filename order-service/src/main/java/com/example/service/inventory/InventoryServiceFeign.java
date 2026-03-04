package com.example.service.inventory;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class InventoryServiceFeign {

    private final InventoryServiceFeignClient inventoryServiceFeignClient;
    public String getTest() {
       String response = inventoryServiceFeignClient.getTest();
       return "response from inventory service: " + response;
    }
}
