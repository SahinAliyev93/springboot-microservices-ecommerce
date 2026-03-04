package com.example.service.consumer;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class NotificationConsumer {
    @JmsListener(destination = "notification-queue")
    public void receive(String message) {
        System.out.println("Notification received: " + message);
    }
}
