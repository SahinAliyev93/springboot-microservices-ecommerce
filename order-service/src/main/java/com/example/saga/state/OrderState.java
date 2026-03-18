package com.example.saga.state;

public enum OrderState {
    ORDER_CREATED,
    PAYMENT_PROCESSING,
    INVENTORY_RESERVING,
    ORDER_COMPLETED,
    ORDER_FAILED;

    public static OrderState fromValue(String value) {
        if (value == null) {
            return null;
        }

        try {
            return OrderState.valueOf(value);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Unknown status: " + value);
        }
    }
}
