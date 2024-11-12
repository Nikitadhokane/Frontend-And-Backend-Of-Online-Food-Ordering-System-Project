package com.project.demo.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.demo.models.Category;
import com.project.demo.models.Food;


@Repository
public interface FoodRepository extends JpaRepository<Food, Integer> {

	List<Food> findByCategory(Category cat);
	List<Food> findByFnameOrDescrContaining(String t1,String t2);
}
