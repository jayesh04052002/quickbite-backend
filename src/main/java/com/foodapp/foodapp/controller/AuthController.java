package com.foodapp.foodapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.foodapp.foodapp.model.User;
import com.foodapp.foodapp.repository.UserRepository;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")  // you can change later to your vercel domain
public class AuthController {

    @Autowired
    private UserRepository repo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {

        String email = user.getEmail() == null ? null : user.getEmail().trim().toLowerCase();
        user.setEmail(email);

        User existing = repo.findByEmail(email);
        if (existing != null) {
            return ResponseEntity.badRequest().body("Email already registered");
        }

        if (user.getRole() == null || user.getRole().isBlank()) {
            user.setRole("USER");
        }

        // ✅ encode before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        User saved = repo.save(user);
        saved.setPassword(null); // optional: don't send password back
        return ResponseEntity.ok(saved);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {

        String email = user.getEmail() == null ? null : user.getEmail().trim().toLowerCase();
        String rawPassword = user.getPassword() == null ? "" : user.getPassword();

        User u = repo.findByEmail(email);

        // ✅ matches(raw, encoded)
        if (u != null && passwordEncoder.matches(rawPassword, u.getPassword())) {
            u.setPassword(null); // optional: don't send password back
            //return ResponseEntity.ok(u);
        }else if(u== null){
        	return ResponseEntity.status(401).body("Invalid Credentials");
        }

        return ResponseEntity.ok(u);
    }
}