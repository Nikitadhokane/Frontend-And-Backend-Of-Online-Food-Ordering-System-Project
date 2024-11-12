package com.project.demo.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.project.demo.models.Address;
import com.project.demo.models.Payment;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class PlaceOrderDTO {

	private Address address;
	private Payment payment;
	@NotNull(message = "Customer ID must not be null")
	private int customerid;
	@NotBlank(message = "Payment method must not be blank")
	private String paymethod;
	
	
	
}
