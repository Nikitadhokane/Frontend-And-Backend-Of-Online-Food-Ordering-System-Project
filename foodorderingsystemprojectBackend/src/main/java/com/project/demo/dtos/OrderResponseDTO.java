package com.project.demo.dtos;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.demo.models.Order;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderResponseDTO {

	private Order order;
	private List<OrderDetailsDTO> details;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDateTime orderDate;
	
	
	
}
