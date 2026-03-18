package com.example.saga.interceptor;

import com.example.model.Order;
import com.example.repository.OrderRepository;
import com.example.saga.event.OrderEvent;
import com.example.saga.state.OrderState;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.support.StateMachineInterceptorAdapter;
import org.springframework.statemachine.transition.Transition;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SagaStateInterceptor extends StateMachineInterceptorAdapter<OrderState, OrderEvent> {
    private final OrderRepository orderRepository;

    @Override
    public void preStateChange(State<OrderState, OrderEvent> state, Message<OrderEvent> message, Transition<OrderState, OrderEvent> transition, StateMachine<OrderState, OrderEvent> stateMachine, StateMachine<OrderState, OrderEvent> rootStateMachine) {

        Order order = (Order) stateMachine.getExtendedState()
                .get("order", Order.class);

        if(order != null) {
            order.setStatus(state.getId().name());
            orderRepository.save(order);
        }
    }
}
