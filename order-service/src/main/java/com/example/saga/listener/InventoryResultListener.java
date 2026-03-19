package com.example.saga.listener;

import com.example.dto.InventoryResult;
import com.example.saga.event.OrderEvent;
import com.example.saga.service.OrderSageService;
import com.example.saga.state.OrderState;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.statemachine.StateMachine;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InventoryResultListener {

    private final OrderSageService orderSageService;

    @KafkaListener(topics = "inventory-result")
    public void handleInventoryResult(InventoryResult result) {
        StateMachine<OrderState, OrderEvent> stateMachine = orderSageService.buildMachine(result.getOrderId());
        if (result.isReserved()) {
            stateMachine.sendEvent(OrderEvent.INVENTORY_SUCCESS);
        } else {
            stateMachine.sendEvent(OrderEvent.INVENTORY_FAILED);
        }
    }
}
