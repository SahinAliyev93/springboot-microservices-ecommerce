package com.example.service.payment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class PaymentClient {

    // Refund payment
    public void refundPayment(Long orderId) {
        // Simulate refund logic
        System.out.println("Refunding payment for order ID: " + orderId);
    }
}
