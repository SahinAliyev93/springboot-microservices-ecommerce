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


    public StateMachine<OrderState,OrderEvent> buildMachine(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));

        StateMachine<OrderState,OrderEvent> sm = stateMachineFactory.getStateMachine(orderId.toString());

        sm.stop();
        sm.getStateMachineAccessor().doWithAllRegions(access ->{
            access.resetStateMachineReactively(new DefaultStateMachineContext<>(OrderState.fromValue(order.getStatus()),
                    null, null, null)).block();
        });
        sm.start();
       return sm;

    }

}
