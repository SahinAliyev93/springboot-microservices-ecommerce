package com.example.mapper;

import com.example.dto.PaymentRequest;
import com.example.dto.PaymentResponse;
import com.example.model.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = DateMapper.class)
public interface PaymentMapper {

    Payment toEntity(PaymentRequest paymentRequest);

    PaymentResponse toResponse(Payment payment);
}
