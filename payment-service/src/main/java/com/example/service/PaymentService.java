package com.example.service;

import com.example.dto.PaymentRequest;
import com.example.dto.PaymentResponse;
import com.example.mapper.PaymentMapper;
import com.example.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;

    public PaymentResponse createPayment(PaymentRequest paymentRequest) {
        // Create a new payment entity
        var payment = paymentMapper.toEntity(paymentRequest);

        // Save the payment to the database
        var savedPayment = paymentRepository.save(payment);

        // Convert the saved payment to a response DTO and return it
        return paymentMapper.toResponse(savedPayment);
    }
}
