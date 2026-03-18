package com.example.saga.service;


import com.example.model.Order;
import com.example.repository.OrderRepository;
import com.example.saga.event.OrderEvent;
import com.example.saga.state.OrderState;
import lombok.RequiredArgsConstructor;

import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.support.DefaultStateMachineContext;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class OrderSageService {

    private final StateMachineFactory<OrderState, OrderEvent> stateMachineFactory;

    private final OrderRepository orderRepository;


    public void handleEvent(Long orderId, OrderEvent event) {
     Order order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));

        StateMachine<OrderState,OrderEvent> sm = stateMachineFactory.getStateMachine(orderId.toString());

        resetStateMachine(sm, OrderState.fromValue(order.getStatus()));

        sm.getExtendedState()
                .getVariables()
                .put("order", order);

        sm.start();

        sm.sendEvent(event);
    }

    private  void resetStateMachine(StateMachine<OrderState, OrderEvent> sm, OrderState state) {
        sm.stop();
       sm.getStateMachineAccessor().doWithAllRegions(access ->{
           access.resetStateMachineReactively(new DefaultStateMachineContext<>(state, null, null, null)).block();
       });
        sm.start();
    }
}
