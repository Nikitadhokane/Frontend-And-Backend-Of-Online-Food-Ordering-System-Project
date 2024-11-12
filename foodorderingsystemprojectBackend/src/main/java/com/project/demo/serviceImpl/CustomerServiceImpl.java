package com.project.demo.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.demo.models.Customer;
import com.project.demo.repos.CustomerRepository;
import com.project.demo.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository dao;
    
    @Override
    public void registerCustomer(Customer cust) {
        dao.save(cust);
    }

    public void saveCustomer(Customer customer) {
       dao.save(customer);
    }
    
    public List<Customer> allCustomers() {
        return dao.findAll();
    }
    
    public Customer findById(int id) {
        return dao.findById(id).orElse(null);
    }

    @Override
    public Customer validate(String userid, String pwd) {
        Customer cc = dao.findByUserid(userid);
        if (cc != null && cc.getPwd().equals(pwd)) {
            return cc;
        }
        return null;
    }

    @Override
    public boolean verifyUserId(String userid) {
        return dao.findByUserid(userid) != null;
    }

    @Override
    public void updateProfile(Customer customer) {
        if (customer.getPwd().equals("") || customer.getPwd() == null) {
            customer.setPwd(findById(customer.getId()).getPwd());
        }
        dao.save(customer);
    }
    
    @Override
    public void deleteUser(int id) {
        dao.deleteById(id);
    }
}