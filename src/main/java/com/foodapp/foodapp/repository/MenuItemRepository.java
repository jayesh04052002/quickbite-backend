package com.foodapp.foodapp.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.foodapp.foodapp.model.MenuItem;

public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
    List<MenuItem> findByRestaurantId(Long restaurantId);
}