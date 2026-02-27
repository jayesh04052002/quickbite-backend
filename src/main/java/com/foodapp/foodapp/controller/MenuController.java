package com.foodapp.foodapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.foodapp.foodapp.model.MenuItem;
import com.foodapp.foodapp.model.Restaurant;
import com.foodapp.foodapp.repository.MenuItemRepository;
import com.foodapp.foodapp.repository.RestaurantRepository;

@RestController
@RequestMapping("/restaurants")
@CrossOrigin(origins = "http://localhost:3000")
public class MenuController {

    @Autowired
    private MenuItemRepository menuRepo;

    @Autowired
    private RestaurantRepository restaurantRepo;

    // ✅ GET menu items
    @GetMapping("/{id}/menu")
    public List<MenuItem> getMenu(@PathVariable Long id) {
        return menuRepo.findByRestaurantId(id);
    }

    // ✅ POST add menu item
    @PostMapping("/{id}/menu")
    public MenuItem addMenu(@PathVariable Long id, @RequestBody MenuItem item) {
        Restaurant r = restaurantRepo.findById(id).orElseThrow();
        item.setRestaurant(r);
        return menuRepo.save(item);
    }
}