package com.example.ordertracker.service;

import com.example.ordertracker.model.OrderStatusUpdate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class KafkaProducerService {

    private static final Logger logger = LoggerFactory.getLogger(KafkaProducerService.class);
    private static final String TOPIC = "order-status-updates";

    private final KafkaTemplate<String, OrderStatusUpdate> kafkaTemplate;

    public KafkaProducerService(KafkaTemplate<String, OrderStatusUpdate> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendOrderStatusUpdate(OrderStatusUpdate update) {
        logger.info("Sending order status update to Kafka: {}", update.getOrderId());
        
        CompletableFuture<SendResult<String, OrderStatusUpdate>> future = 
            kafkaTemplate.send(TOPIC, update.getOrderId(), update);
            
        future.whenComplete((result, exception) -> {
            if (exception == null) {
                logger.info("Sent message=[{}] with offset=[{}]", 
                    update.getOrderId(), result.getRecordMetadata().offset());
            } else {
                logger.error("Unable to send message=[{}] due to : {}", 
                    update.getOrderId(), exception.getMessage());
            }
        });
    }
}
