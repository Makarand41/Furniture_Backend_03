package com.nanotech.furniture.service;

import com.nanotech.furniture.entity.Order;
import com.nanotech.furniture.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    // ✅ Create Order
    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    // ✅ Get all Orders
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    // ✅ Get Order by ID
    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id " + id));
    }


    // ✅ Update Order
    public Order updateOrder(Long id, Order updatedOrder) {
        return orderRepository.findById(id)
                .map(existingOrder -> {
                    existingOrder.setCustomerName(updatedOrder.getCustomerName());
                    existingOrder.setProductName(updatedOrder.getProductName());
                    existingOrder.setQuantity(updatedOrder.getQuantity());
                    existingOrder.setOrderDate(updatedOrder.getOrderDate());
                    existingOrder.setDeliveryDate(updatedOrder.getDeliveryDate());
                    return orderRepository.save(existingOrder);
                })
                .orElseThrow(() -> new RuntimeException("Order not found with id " + id));
    }

    // ✅ Delete Order
    public void deleteOrder(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new RuntimeException("Order not found with id " + id);
        }
        orderRepository.deleteById(id);
    }
    
    
    
}
