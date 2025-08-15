package com.example.ordertracker.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;

public class Order {
    private String orderId;
    private String customerName;
    private String status;
    private String description;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastUpdated;

    public Order() {}

    public Order(String orderId, String customerName, String status, String description) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.status = status;
        this.description = description;
        this.lastUpdated = LocalDateTime.now();
    }

    // Getters and Setters
    public String getOrderId() { return orderId; }
    public void setOrderId(String orderId) { this.orderId = orderId; }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public String getStatus() { return status; }
    public void setStatus(String status) { 
        this.status = status;
        this.lastUpdated = LocalDateTime.now();
    }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public LocalDateTime getLastUpdated() { return lastUpdated; }
    public void setLastUpdated(LocalDateTime lastUpdated) { this.lastUpdated = lastUpdated; }
}
