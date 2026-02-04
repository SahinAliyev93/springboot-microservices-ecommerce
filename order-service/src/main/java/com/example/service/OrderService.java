package com.example.service;

import com.example.dto.OrderRequest;
import com.example.dto.OrderResponse;
import com.example.mapper.OrderMapper;
import com.example.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public OrderResponse createOrder(OrderRequest orderRequest) {
        var order = orderMapper.toEntity(orderRequest);
        var savedOrder = orderRepository.save(order);
        return orderMapper.toResponse(savedOrder);
    }
}
