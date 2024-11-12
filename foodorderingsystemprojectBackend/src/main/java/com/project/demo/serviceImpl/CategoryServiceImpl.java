package com.project.demo.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.demo.models.Category;
import com.project.demo.repos.CategoryRepository;
import com.project.demo.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository repo;

    @Override
    public void save(Category category) {
        repo.save(category);
    }

    @Override
    public List<Category> listAll() {
        return repo.findAll();
    }

    @Override
    public Category findById(int id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public void deleteCategory(int id) {
        repo.deleteById(id);
    }
    
    @Override
    public void updateCategory(int id, Category updatedCategory) {
        Category category = repo.findById(id).orElse(null);
        if (category != null) {
            category.setCatname(updatedCategory.getCatname());
            repo.save(category);
        }
    }
}
