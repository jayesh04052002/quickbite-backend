package com.foodapp.foodapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.foodapp.foodapp.model.User;
import com.foodapp.foodapp.repository.UserRepository;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    UserRepository repo;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {

        // optional: check email already exists
        User existing = repo.findByEmail(user.getEmail());
        if (existing != null) {
            return ResponseEntity.badRequest().body("Email already registered");
        }

        if (user.getRole() == null || user.getRole().isBlank()) {
            user.setRole("USER");
        }

        User saved = repo.save(user);
        return ResponseEntity.ok(saved);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        User u = repo.findByEmail(user.getEmail());

        if (u != null && u.getPassword().equals(user.getPassword())) {
            return ResponseEntity.ok(u); // ✅ return full user object
        }

        return ResponseEntity.status(401).body("Invalid Credentials");
    }
}