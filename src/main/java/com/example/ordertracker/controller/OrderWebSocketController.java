package com.example.ordertracker.controller;

import com.example.ordertracker.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class OrderWebSocketController {

    private final OrderService orderService;

    public OrderWebSocketController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/admin/update-status")
    @ResponseBody
    public String updateOrderStatus(@RequestParam String orderId, 
                                   @RequestParam String status, 
                                   @RequestParam String message) {
        orderService.updateOrderStatus(orderId, status, message, "ADMIN");
        return "success";
    }

    @PostMapping("/admin/simulate-external")
    @ResponseBody
    public String simulateExternalUpdate(@RequestParam String orderId) {
        orderService.simulateExternalUpdate(orderId);
        return "success";
    }
}
