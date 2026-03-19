package com.example.saga.action;

import com.example.model.Order;
import com.example.saga.event.OrderEvent;
import com.example.saga.state.OrderState;
import com.example.service.payment.PaymentClient;
import lombok.RequiredArgsConstructor;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RefundAction implements Action<OrderState, OrderEvent> {

    private final PaymentClient paymentClient;

    @Override
    public void execute(StateContext<OrderState, OrderEvent> context) {

        Order order = (Order) context.getExtendedState()
                .get("order", Order.class);

        paymentClient.refundPayment(order.getId());

        System.out.println("Payment refunded!");
    }
}
