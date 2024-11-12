package com.project.demo.service;

import java.util.List;

import com.project.demo.dtos.WishlistDTO;
import com.project.demo.models.Food;
import com.project.demo.models.Wishlist;

public interface WishlistService {
	
	void save(WishlistDTO dto);
    List<Wishlist> findByUserId(int id);
    void deleteItem(int id);
    boolean checkExist(int custid, Food food);

}
