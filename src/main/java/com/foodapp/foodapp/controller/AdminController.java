package com.foodapp.foodapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foodapp.foodapp.model.Order;
import com.foodapp.foodapp.model.Restaurant;
import com.foodapp.foodapp.model.User;
import com.foodapp.foodapp.repository.OrderRepository;
import com.foodapp.foodapp.repository.RestaurantRepository;
import com.foodapp.foodapp.repository.UserRepository;



@RestController
@RequestMapping("/admin")
@CrossOrigin
public class AdminController {

    @Autowired UserRepository userRepo;
    @Autowired RestaurantRepository restRepo;
    @Autowired OrderRepository orderRepo;

    @GetMapping("/users")
    public List<User> users(){ return userRepo.findAll(); }

    @GetMapping("/restaurants")
    public List<Restaurant> restaurants(){ return restRepo.findAll(); }

    @GetMapping("/orders")
    public List<Order> orders(){ return orderRepo.findAll(); }
}