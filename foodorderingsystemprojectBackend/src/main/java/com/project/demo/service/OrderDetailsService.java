package com.project.demo.service;

import java.util.List;

import com.project.demo.models.Order;
import com.project.demo.models.OrderDetails;

public interface OrderDetailsService {
	
	void saveOrderDetails(OrderDetails od);
    OrderDetails findById(int id);
    List<OrderDetails> findByOrder(Order order);

}
