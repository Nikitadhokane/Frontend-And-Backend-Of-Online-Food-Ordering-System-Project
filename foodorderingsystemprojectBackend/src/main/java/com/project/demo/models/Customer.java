package com.project.demo.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(length=20)
	private String name;
	@Column(length=20)
	private String city;
	private String userid;
	@Column(length=255)
	private String pwd;
	@Column(length=10)
	private String phone;
	@Column(length=6)
	private String gender;
	
	
	public Customer(int id) {
		this.id = id;
	}
	
	
}
