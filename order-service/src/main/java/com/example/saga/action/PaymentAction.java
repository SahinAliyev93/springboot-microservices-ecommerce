package com.example.saga.action;

import com.example.dto.PaymentRequest;
import com.example.model.Order;
import com.example.saga.event.OrderEvent;
import com.example.saga.state.OrderState;
import com.example.service.producer.PaymentMessageProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class PaymentAction implements Action<OrderState, OrderEvent> {

    private final PaymentMessageProducer paymentMessageProducer;

    @Override
    public void execute(StateContext<OrderState, OrderEvent> context) {
        Order order = (Order) context.getExtendedState().getVariables().get("order");
        paymentMessageProducer.sendPaymentRequest(
                PaymentRequest.builder().orderId(order.getId())
                        .amount(BigDecimal.valueOf(order.getQuantity()).multiply(order.getPrice()))
                        .build());
    }
}
