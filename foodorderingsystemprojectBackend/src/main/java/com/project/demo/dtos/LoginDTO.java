package com.project.demo.dtos;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LoginDTO {
	
	@NotBlank(message = "User ID cannot be blank")
	private String userid;
	
	@NotBlank(message = "Password cannot be blank")
	private String pwd;
	
	@NotBlank(message = "Role cannot be blank")
	private String role;
	
}
