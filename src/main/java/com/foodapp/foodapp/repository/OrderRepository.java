package com.foodapp.foodapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import com.foodapp.foodapp.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("select o from Order o left join fetch o.items where o.id = :id")
    Optional<Order> findByIdWithItems(@Param("id") Long id);
}