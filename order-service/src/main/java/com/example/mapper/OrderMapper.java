package com.example.mapper;

import com.example.dto.OrderRequest;
import com.example.dto.OrderResponse;
import com.example.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface OrderMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", constant = "CREATED")
    @Mapping(target = "createdAt", expression = "java(LocalDateTime.now())")
    Order toEntity(OrderRequest orderRequest);

    OrderResponse toResponse(Order order);
}
