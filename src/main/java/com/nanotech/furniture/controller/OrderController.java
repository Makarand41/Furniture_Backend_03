package com.nanotech.furniture.controller;

import com.nanotech.furniture.entity.Order;
import com.nanotech.furniture.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "*") // ✅ allows frontend access (React, etc.)
public class OrderController {

    @Autowired
    private OrderService orderService;

    // ✅ Create Order
    @PostMapping("/create")
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        Order savedOrder = orderService.createOrder(order);
        return ResponseEntity.ok(savedOrder);
    }

    // ✅ Get All Orders
    @GetMapping("/all")
    public ResponseEntity<List<Order>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    // ✅ Get Single Order by ID
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable("id") Long id) {
        Order order = orderService.getOrderById(id);
        return ResponseEntity.ok(order);
    }

    // ✅ Update Order
    @PutMapping("/update/{id}")
    public ResponseEntity<Order> updateOrder(
            @PathVariable("id") Long id,
            @RequestBody Order updatedOrder
    ) {
        Order updated = orderService.updateOrder(id, updatedOrder);
        return ResponseEntity.ok(updated);
    }

    // ✅ Delete Order
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable("id") Long id) {
        try {
            orderService.deleteOrder(id);
            return ResponseEntity.ok("Order deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Failed to delete order: " + e.getMessage());
        }
    }
}
