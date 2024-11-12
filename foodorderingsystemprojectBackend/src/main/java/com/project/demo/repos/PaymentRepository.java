package com.project.demo.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.demo.models.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {

}
