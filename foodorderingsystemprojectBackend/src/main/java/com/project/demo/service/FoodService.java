package com.project.demo.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.project.demo.models.Food;

public interface FoodService {
	
	void addFood(Food food, MultipartFile pic);
    //void updateFood(Food food);
	void updateFood(Food food, MultipartFile pic);
    void deleteFood(int id);
    List<Food> allFoods();
    List<Food> searchFoods(String search);
    Food findFoodById(int fodid);
    List<Food> categoryFoods(int catid);
}


