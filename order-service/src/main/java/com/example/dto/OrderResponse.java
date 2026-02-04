package com.example.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class OrderResponse {
    private Long id;
    private String productCode;
    private Integer quantity;
    private BigDecimal price;
    private String status;
    private LocalDateTime createdAt;
}
