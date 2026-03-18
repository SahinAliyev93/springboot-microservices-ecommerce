package com.example.saga.event;

public enum OrderEvent {
    START_PAYMENT,
    PAYMENT_SUCCESS,
    PAYMENT_FAILED,
    INVENTORY_SUCCESS,
    INVENTORY_FAILED
}

