package com.example.service.producer;

import lombok.RequiredArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationActivemqProducer {

    private final JmsTemplate jmsTemplate;

    public void send(String message) {
        jmsTemplate.convertAndSend("notification-queue", message);
    }

}
