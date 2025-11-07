package com.nanotech.furniture.repository;


import com.nanotech.furniture.entity.Order;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    // Example custom query methods (optional)
    // Find orders by customer name
    List<Order> findByCustomerNameContainingIgnoreCase(String customerName);

    // Find all orders for a specific product
    List<Order> findByProductNameContainingIgnoreCase(String productName);
}
