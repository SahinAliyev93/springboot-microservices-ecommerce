package com.example.saga.config;


import com.example.saga.action.PaymentAction;
import com.example.saga.event.OrderEvent;
import com.example.saga.guard.PaymentSuccessGuard;
import com.example.saga.state.OrderState;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

@Configuration
@EnableStateMachineFactory
@Slf4j
public class OrderStateMachineConfig extends StateMachineConfigurerAdapter<OrderState, OrderEvent> {

    private final PaymentAction paymentAction;
    private final PaymentSuccessGuard paymentSuccessGuard;

    public OrderStateMachineConfig(PaymentAction paymentAction,PaymentSuccessGuard paymentSuccessGuard) {
        this.paymentAction = paymentAction;
        this.paymentSuccessGuard = paymentSuccessGuard;
    }

    @Override
    public void configure(StateMachineStateConfigurer<OrderState, OrderEvent> states) throws Exception {
        states.withStates()
                .initial(OrderState.ORDER_CREATED)
                .state(OrderState.PAYMENT_PROCESSING)
                .state(OrderState.INVENTORY_RESERVING)
                .end(OrderState.ORDER_COMPLETED)
                .end(OrderState.ORDER_FAILED);
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<OrderState, OrderEvent> transitions) throws Exception {
        transitions
                .withExternal()
                .source(OrderState.ORDER_CREATED)
                .target(OrderState.PAYMENT_PROCESSING)
                .event(OrderEvent.START_PAYMENT)
                .action(paymentAction)
                .and()
                .withExternal()
                .source(OrderState.PAYMENT_PROCESSING)
                .target(OrderState.INVENTORY_RESERVING)
                .event(OrderEvent.PAYMENT_SUCCESS)
                .guard(paymentSuccessGuard)
                .and()
                .withExternal()
                .source(OrderState.PAYMENT_PROCESSING)
                .target(OrderState.ORDER_FAILED)
                .event(OrderEvent.PAYMENT_FAILED);
    }

}
