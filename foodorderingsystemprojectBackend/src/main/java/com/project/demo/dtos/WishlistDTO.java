package com.project.demo.dtos;

import javax.validation.constraints.NotNull;

import com.project.demo.models.Wishlist;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WishlistDTO extends Wishlist {

	@NotNull(message = "Customer ID must not be null")
	private int custid;

	
	
}
