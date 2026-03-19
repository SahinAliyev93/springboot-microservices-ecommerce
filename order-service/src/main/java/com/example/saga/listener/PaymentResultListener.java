package com.example.saga.listener;

import com.example.dto.PaymentResult;
import com.example.saga.event.OrderEvent;
import com.example.saga.service.OrderSageService;
import lombok.AllArgsConstructor;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PaymentResultListener {
private final OrderSageService orderSageService;

    @KafkaListener(topics = "payment-result-queue", groupId = "order-service")
    public void handlePaymentResult(PaymentResult result) {
        var stateMachine = orderSageService.buildMachine(result.getOrderId());
        stateMachine.getExtendedState()
                .getVariables()
                .put("paymentSuccess", result.isSuccess());

        if (result.isSuccess()) {
            stateMachine.sendEvent(OrderEvent.PAYMENT_SUCCESS);
        } else {
            stateMachine.sendEvent(OrderEvent.PAYMENT_FAILED);
        }
    }
}
