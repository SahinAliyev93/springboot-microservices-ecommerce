package com.example.saga.state;

public enum OrderState {
    ORDER_CREATED,
    PAYMENT_PROCESSING,
    INVENTORY_RESERVING,
    ORDER_COMPLETED,
    ORDER_FAILED
}
