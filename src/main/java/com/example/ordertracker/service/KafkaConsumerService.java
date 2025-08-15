package com.example.ordertracker.service;

import com.example.ordertracker.model.OrderStatusUpdate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumerService.class);

    private final SimpMessagingTemplate messagingTemplate;

    public KafkaConsumerService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @KafkaListener(topics = "order-status-updates", groupId = "order-tracker-group")
    public void consumeOrderStatusUpdate(OrderStatusUpdate update) {
        logger.info("Consumed order status update from Kafka: {} - {}", 
            update.getOrderId(), update.getStatus());
        
        try {
            // Gửi update qua WebSocket đến tất cả clients
            messagingTemplate.convertAndSend("/topic/order-updates", update);
            logger.info("Sent WebSocket message for order: {}", update.getOrderId());
        } catch (Exception e) {
            logger.error("Error sending WebSocket message: {}", e.getMessage());
        }
    }
}
