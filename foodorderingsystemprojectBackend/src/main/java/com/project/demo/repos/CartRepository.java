package com.project.demo.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.demo.models.Cart;
import com.project.demo.models.Customer;
import com.project.demo.models.Food;


@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

	List<Cart> findByCustomer(Customer cust);
	Cart findByCustomerAndFood(Customer customer,Food food);
	void deleteByCustomer(Customer cust);
}
