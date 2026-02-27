package com.foodapp.foodapp.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.foodapp.foodapp.model.Restaurant;
import com.foodapp.foodapp.repository.RestaurantRepository;

@RestController
@RequestMapping("/restaurants")
@CrossOrigin(origins = "http://localhost:3000")
public class RestaurantController {

    @Autowired
    private RestaurantRepository repo;

    @GetMapping
    public List<Restaurant> getAll() {
        return repo.findAll();
    }

    @PostMapping
    public Restaurant add(@RequestBody Restaurant r) {
        return repo.save(r);
    }

    @GetMapping("/{id}")
    public Restaurant getById(@PathVariable Long id) {
        return repo.findById(id).orElse(null);
    }
}
