package com.project.demo.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.demo.models.Customer;
import com.project.demo.models.Food;
import com.project.demo.models.Wishlist;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Integer> {
	
	List<Wishlist> findByCustomer(Customer customer);
	Wishlist findByCustomerAndFood(Customer cust,Food fod);

}
