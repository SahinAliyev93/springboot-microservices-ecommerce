package com.example.service;

import com.example.dto.OrderRequest;
import com.example.dto.OrderResponse;
import com.example.kafkaEvent.OrderCreatedEvent;
import com.example.mapper.OrderMapper;
import com.example.repository.OrderRepository;
import com.example.service.producer.OrderEventProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final OrderEventProducer orderEventProducer;
    public OrderResponse createOrder(OrderRequest orderRequest) {
        var order = orderMapper.toEntity(orderRequest);
        var savedOrder = orderRepository.save(order);
        OrderCreatedEvent event = new OrderCreatedEvent(
                order.getId(),
                order.getProductCode(),
                order.getQuantity(),
                order.getPrice()
        );

        orderEventProducer.sendOrderCreatedEvent(event);
        return orderMapper.toResponse(savedOrder);
    }
}
