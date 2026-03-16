package com.example.saga.listener;

import com.example.saga.event.OrderEvent;
import com.example.saga.state.OrderState;
import lombok.extern.slf4j.Slf4j;
import org.springframework.statemachine.annotation.EventHeaders;
import org.springframework.statemachine.annotation.OnStateChanged;
import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.annotation.WithStateMachine;
import org.springframework.stereotype.Component;
import org.springframework.statemachine.state.State;

import java.util.Map;

@Component
@WithStateMachine
@Slf4j
public class OrderStateMachineListener {

    @OnTransition(source = "ORDER_CREATED", target = "PAYMENT_PROCESSING")
    public void onPaymentStart() {
        log.info("Payment Start...");
    }

    @OnTransition
    public void anyTransition(
            @EventHeaders Map<String, Object> headers,
             State<OrderState, OrderEvent> to) {

        log.info("Status change: {}", to.getId());
    }

    @OnStateChanged
    public void stateChanged(
            State<OrderState, OrderEvent> to) {

        log.info("Status change: {}", to.getId());

    }
}