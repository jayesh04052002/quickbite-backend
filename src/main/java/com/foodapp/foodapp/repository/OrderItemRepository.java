package com.foodapp.foodapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.foodapp.foodapp.model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}