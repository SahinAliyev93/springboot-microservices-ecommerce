package com.example.dto;

import lombok.Data;

@Data
public class InventoryRequest {
    private String productCode;
    private Integer availableQuantity;
}
