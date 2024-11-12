package com.project.demo.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.demo.dtos.WishlistDTO;
import com.project.demo.models.Food;
import com.project.demo.models.Wishlist;
import com.project.demo.repos.WishlistRepository;
import com.project.demo.service.CustomerService;
import com.project.demo.service.WishlistService;

@Service
public class WishlistServiceImpl implements WishlistService {

    @Autowired 
    private WishlistRepository repo;
    
    @Autowired 
    private CustomerService cservice;
    
    @Override
    public void save(WishlistDTO dto) {
        Wishlist wl = new Wishlist();
        wl.setFood(dto.getFood());
        wl.setCustomer(cservice.findById(dto.getCustid()));
        repo.save(wl);
    }

    @Override
    public List<Wishlist> findByUserId(int id) {
        return repo.findByCustomer(cservice.findById(id));
    }

    @Override
    public void deleteItem(int id) {
        repo.deleteById(id);
    }

    @Override
    public boolean checkExist(int custid, Food food) {
        return repo.findByCustomerAndFood(cservice.findById(custid), food) != null;
    }
}