package com.project.demo.dtos;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.beans.BeanUtils;

import com.project.demo.models.Food;
import com.project.demo.models.OrderDetails;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDetailsDTO {
	
	private int id;
	
	@NotNull(message = "Food cannot be null")
	private Food food;
	
	@Min(value = 1, message = "Quantity must be at least 1")
	private int qty;
	

	public static OrderDetailsDTO fromEntity(OrderDetails entity) {
		OrderDetailsDTO dto = new OrderDetailsDTO();
		BeanUtils.copyProperties(entity, dto);		
		return dto;
	}
}
