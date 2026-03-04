package com.example.kafkaEvent;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventoryCreatedEvent {

    private Long inventoryId;
    private String productCode;
    private Integer availableQuantity;
}
