package com.project.demo.service;

import java.util.List;

import com.project.demo.models.Category;

public interface CategoryService {
	
	void save(Category category);
    List<Category> listAll();
    Category findById(int id);
    void deleteCategory(int id);
    void updateCategory(int id, Category updatedCategory);

}
