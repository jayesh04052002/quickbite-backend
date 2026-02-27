package com.foodapp.foodapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.foodapp.foodapp.model.Order;
import com.foodapp.foodapp.model.Payment;
import com.foodapp.foodapp.repository.OrderRepository;
import com.foodapp.foodapp.repository.PaymentRepository;

@RestController
@RequestMapping("/payments")
@CrossOrigin(origins = "http://localhost:3000")
public class PaymentController {

    @Autowired private PaymentRepository paymentRepo;
    @Autowired private OrderRepository orderRepo;

    @PostMapping
    public Payment pay(@RequestBody Payment p) {
        Order order = orderRepo.findById(p.getOrderId()).orElseThrow();
        p.setAmount(order.getTotal());
        p.setStatus("PAID");

        order.setStatus("PAID");
        orderRepo.save(order);

        return paymentRepo.save(p);
    }
}