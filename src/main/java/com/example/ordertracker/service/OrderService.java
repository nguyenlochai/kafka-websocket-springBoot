package com.example.ordertracker.service;

import com.example.ordertracker.model.Order;
import com.example.ordertracker.model.OrderStatusUpdate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class OrderService {
    
    private final KafkaProducerService kafkaProducerService;
    private final Map<String, Order> orders = new ConcurrentHashMap<>();

    public OrderService(KafkaProducerService kafkaProducerService) {
        this.kafkaProducerService = kafkaProducerService;
        initializeOrders();
    }

    private void initializeOrders() {
        orders.put("ORD001", new Order("ORD001", "Nguyễn Văn A", "PENDING", "Đơn hàng đang chờ xử lý"));
        orders.put("ORD002", new Order("ORD002", "Trần Thị B", "PROCESSING", "Đang chuẩn bị hàng"));
        orders.put("ORD003", new Order("ORD003", "Lê Văn C", "SHIPPED", "Đã giao cho đơn vị vận chuyển"));
        orders.put("ORD004", new Order("ORD004", "Phạm Thị D", "DELIVERED", "Đã giao hàng thành công"));
        orders.put("ORD005", new Order("ORD005", "Hoàng Văn E", "CANCELLED", "Đơn hàng đã bị hủy"));
    }

    public Order getOrder(String orderId) {
        return orders.get(orderId);
    }

    public Map<String, Order> getAllOrders() {
        return new HashMap<>(orders);
    }

    public void updateOrderStatus(String orderId, String newStatus, String message) {
        updateOrderStatus(orderId, newStatus, message, "ADMIN");
    }

    public void updateOrderStatus(String orderId, String newStatus, String message, String source) {
        Order order = orders.get(orderId);
        if (order != null) {
            // Cập nhật order trong memory
            order.setStatus(newStatus);
            order.setDescription(message);
            
            // Tạo update event và gửi qua Kafka
            OrderStatusUpdate update = new OrderStatusUpdate(orderId, newStatus, message, source);
            kafkaProducerService.sendOrderStatusUpdate(update);
        }
    }

    // Simulate external system updates
    public void simulateExternalUpdate(String orderId) {
        Order order = orders.get(orderId);
        if (order != null) {
            String[] statuses = {"PROCESSING", "SHIPPED", "DELIVERED"};
            String[] messages = {
                "Hệ thống tự động: Đang xử lý đơn hàng",
                "Hệ thống vận chuyển: Đã xuất kho",
                "Hệ thống giao hàng: Đã giao thành công"
            };
            
            int randomIndex = (int) (Math.random() * statuses.length);
            updateOrderStatus(orderId, statuses[randomIndex], messages[randomIndex], "SYSTEM");
        }
    }
}
