package com.example.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InventoryResponse {

    private Long id;

    private String productCode;

    private Integer availableQuantity;

    private OffsetDateTime createdAt;

    private OffsetDateTime lastModifiedDate;
}
