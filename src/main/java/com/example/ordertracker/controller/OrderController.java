package com.example.ordertracker.controller;

import com.example.ordertracker.model.Order;
import com.example.ordertracker.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("orders", orderService.getAllOrders());
        return "index";
    }

    @GetMapping("/order/{orderId}")
    public String orderDetail(@PathVariable String orderId, Model model) {
        Order order = orderService.getOrder(orderId);
        if (order != null) {
            model.addAttribute("order", order);
            return "order-detail";
        }
        return "redirect:/";
    }

    @GetMapping("/track")
    public String trackOrder(@RequestParam(required = false) String orderId, Model model) {
        if (orderId != null && !orderId.isEmpty()) {
            Order order = orderService.getOrder(orderId);
            model.addAttribute("order", order);
        }
        return "track-order";
    }
}
