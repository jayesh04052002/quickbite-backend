package com.foodapp.foodapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.foodapp.foodapp.model.Order;
import com.foodapp.foodapp.model.OrderItem;
import com.foodapp.foodapp.repository.OrderRepository;

@RestController
@RequestMapping("/orders")
@CrossOrigin(origins = "http://localhost:3000")
public class OrderController {

    @Autowired
    private OrderRepository orderRepo;

    @PostMapping
    public Order placeOrder(@RequestBody Order order) {
        order.setStatus("CREATED");

        // attach parent reference to each item (important for saving)
        List<OrderItem> items = order.getItems();
        if (items != null) {
            for (OrderItem item : items) {
                item.setOrder(order);
            }
        }

        // auto-calc total (trust backend)
        double total = 0;
        if (items != null) {
            for (OrderItem i : items) {
                total += i.getPrice() * i.getQty();
            }
        }
        order.setTotal(total);

        return orderRepo.save(order);
    }

    @GetMapping("/{id}")
    public Order getOrder(@PathVariable Long id) {
        return orderRepo.findById(id).orElse(null);
    }
}