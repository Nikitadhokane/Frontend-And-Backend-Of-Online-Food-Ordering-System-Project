package com.project.demo.service;

import java.util.List;

import com.project.demo.models.Customer;

public interface CustomerService {
	
	void registerCustomer(Customer cust);
    List<Customer> allCustomers();
    Customer findById(int id);
    Customer validate(String userid, String pwd);
    boolean verifyUserId(String userid);
    void updateProfile(Customer customer);
    void deleteUser(int id);

}
