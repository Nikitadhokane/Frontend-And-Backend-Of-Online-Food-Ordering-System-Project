package com.project.demo.service;

import java.util.List;

import com.project.demo.models.Customer;
import com.project.demo.models.Order;

public interface OrderService {
	
	Order saveOrder(Order order);
    void confirm(int id);
    List<Order> getAllOrders();
    List<Order> getCustomerOrders(Customer customer);
    Order findById(int id);
	Order placeOrder(Order order);

}
