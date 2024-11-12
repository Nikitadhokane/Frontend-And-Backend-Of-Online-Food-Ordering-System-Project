package com.project.demo.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.demo.models.Order;
import com.project.demo.models.OrderDetails;
import com.project.demo.repos.OrderDetailsRepository;
import com.project.demo.service.OrderDetailsService;

@Service
public class OrderDetailsServiceImpl implements OrderDetailsService {

    @Autowired 
    OrderDetailsRepository dao;
    
    @Override
    public void saveOrderDetails(OrderDetails od) {
        dao.save(od);
    }

    @Override
    public OrderDetails findById(int id) {
        return dao.findById(id).get();
    }

    @Override
    public List<OrderDetails> findByOrder(Order order) {
        return dao.findByOrder(order);
    }
}