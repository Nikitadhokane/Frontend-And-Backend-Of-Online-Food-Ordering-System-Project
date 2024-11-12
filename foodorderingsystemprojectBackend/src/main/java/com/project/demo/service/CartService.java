package com.project.demo.service;

import java.util.List;

import com.project.demo.models.Cart;
import com.project.demo.models.Customer;
import com.project.demo.models.Food;

public interface CartService {
	
	void save(Cart cart);
    List<Cart> findByUserId(int id);
    void updateQty(int cartId, int qty);
    void deleteItem(int id);
    boolean checkExist(Customer customer, Food food);
    void clearCart(Customer cust);
	Cart findById(int cartid);

}
