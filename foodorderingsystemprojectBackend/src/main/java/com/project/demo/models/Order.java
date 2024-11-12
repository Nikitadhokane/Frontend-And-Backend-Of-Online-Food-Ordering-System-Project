package com.project.demo.models;


import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name="orders")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int orderid;
	@Column(name = "orderDate", nullable = false)
    private LocalDate orderDate;
	//private LocalDate orderDate;
	@ManyToOne
	@JoinColumn(name="customerId")
	private Customer customer;
	@ManyToOne
	@JoinColumn(name="addressId")
	private Address address;
	@ManyToOne
	@JoinColumn(name="paymentId")
	private Payment payment;
	private String paymethod;
	private String status;
	
	@OneToMany(mappedBy = "order" , fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<OrderDetails> orderdetails;
	
	
	
	
	
	
}
