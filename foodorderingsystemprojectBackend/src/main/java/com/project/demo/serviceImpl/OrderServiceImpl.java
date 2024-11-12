package com.project.demo.serviceImpl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.demo.models.Customer;
import com.project.demo.models.Order;
import com.project.demo.repos.OrderRepository;
import com.project.demo.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired 
    OrderRepository dao;

    @Override
    public Order placeOrder(Order order) {
        order.setOrderDate(LocalDate.now()); // Set the current date and time
        return dao.save(order);
    }

    @Override
    public Order saveOrder(Order order) {
        return dao.save(order);
    }

    @Override
    public void confirm(int id) {
        Order order = dao.findById(id).get();
        order.setStatus("Confirmed");
        dao.save(order);
    }

    @Override
    public List<Order> getAllOrders() {
        return dao.findAll();
    }

    @Override
    public List<Order> getCustomerOrders(Customer customer) {
        return dao.findByCustomer(customer);
    }

    @Override
    public Order findById(int id) {
        return dao.findById(id).get();
    }
}
