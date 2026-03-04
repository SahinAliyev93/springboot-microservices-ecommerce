package com.example.mapper;

import com.example.dto.OrderRequest;
import com.example.dto.OrderResponse;
import com.example.model.Order;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = DateMapper.class)
public interface OrderMapper {

    Order toEntity(OrderRequest orderRequest);

    OrderResponse toResponse(Order order);
}
