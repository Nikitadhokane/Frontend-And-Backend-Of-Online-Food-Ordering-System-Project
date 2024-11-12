package com.project.demo.dtos;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartDTO {

	private int id;
	
	@NotBlank(message = "Category cannot be blank")
	private String cat;
	
	@NotBlank(message = "Item name cannot be blank")
	private String bname;
	
	@Min(value = 0, message = "Price cannot be negative")
	private int price;
	
	@Min(value = 1, message = "Quantity must be at least 1")
	private int qty;
	
	
}
