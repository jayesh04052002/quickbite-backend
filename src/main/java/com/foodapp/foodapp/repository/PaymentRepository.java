package com.foodapp.foodapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.foodapp.foodapp.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}