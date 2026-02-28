package com.foodapp.foodapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "QuickBite backend is running ✅";
    }

    @GetMapping("/health")
    public String health() {
        return "OK";
    }
}