package com.example.saga.guard;

import com.example.saga.event.OrderEvent;
import com.example.saga.state.OrderState;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.guard.Guard;
import org.springframework.stereotype.Component;

@Component
public class PaymentSuccessGuard implements Guard<OrderState, OrderEvent> {
    @Override
    public boolean evaluate(StateContext<OrderState, OrderEvent> context) {
        Boolean paymentSuccess = (Boolean) context.getExtendedState().getVariables().get("paymentSuccess");
        return paymentSuccess != null && paymentSuccess;
    }
}
