package com.crimsonlogic.realestate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crimsonlogic.realestate.model.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {
   
}