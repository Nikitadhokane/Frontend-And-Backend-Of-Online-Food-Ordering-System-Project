package com.project.demo.service;

import com.project.demo.models.Payment;

public interface PaymentService {
	
	Payment savePayment(Payment payment);
    Payment findPaymentById(int id);
	}


