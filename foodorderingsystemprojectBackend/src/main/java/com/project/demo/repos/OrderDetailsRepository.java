package com.project.demo.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.demo.models.Order;
import com.project.demo.models.OrderDetails;

@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Integer> {

	List<OrderDetails> findByOrder(Order order);
}
