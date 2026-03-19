package com.example.service.producer;

import com.example.dto.PaymentRequest;
import com.example.kafkaEvent.OrderCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentMessageProducer {

    private final KafkaTemplate<String, PaymentRequest> kafkaTemplate;


    public void sendPaymentRequest(PaymentRequest request) {
        kafkaTemplate.send("payment-topic", request.getOrderId().toString(), request);
    }
}
