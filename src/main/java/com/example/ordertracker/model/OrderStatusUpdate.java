package com.example.ordertracker.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;

public class OrderStatusUpdate {
    private String orderId;
    private String status;
    private String message;
    private String source; // Nguồn cập nhật: ADMIN, SYSTEM, EXTERNAL

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime timestamp;

    public OrderStatusUpdate() {}

    public OrderStatusUpdate(String orderId, String status, String message) {
        this.orderId = orderId;
        this.status = status;
        this.message = message;
        this.source = "ADMIN";
        this.timestamp = LocalDateTime.now();
    }

    public OrderStatusUpdate(String orderId, String status, String message, String source) {
        this.orderId = orderId;
        this.status = status;
        this.message = message;
        this.source = source;
        this.timestamp = LocalDateTime.now();
    }

    // Getters and Setters
    public String getOrderId() { return orderId; }
    public void setOrderId(String orderId) { this.orderId = orderId; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public String getSource() { return source; }
    public void setSource(String source) { this.source = source; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
}
