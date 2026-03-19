package com.example.saga.action;

import com.example.model.Order;
import com.example.saga.event.OrderEvent;
import com.example.saga.state.OrderState;
import com.example.service.inventory.InventoryServiceFeign;

import lombok.RequiredArgsConstructor;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InventoryAction implements Action<OrderState, OrderEvent> {

    private final InventoryServiceFeign inventoryClient;

    @Override
    public void execute(StateContext<OrderState, OrderEvent> context) {

        Order order = (Order) context.getExtendedState()
                .get("order", Order.class);

        inventoryClient.getTest();

        System.out.println("Inventory request sent");
    }
}
