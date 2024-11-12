package com.project.demo.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.demo.models.Payment;
import com.project.demo.repos.PaymentRepository;
import com.project.demo.service.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired 
    PaymentRepository dao;
    
    @Override
    public Payment savePayment(Payment payment) {
        return dao.save(payment);
    }

    @Override
    public Payment findPaymentById(int id) {
        return dao.findById(id).get();
    }
}