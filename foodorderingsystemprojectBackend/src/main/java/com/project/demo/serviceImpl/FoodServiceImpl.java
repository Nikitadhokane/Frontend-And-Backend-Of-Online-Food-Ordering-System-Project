package com.project.demo.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.project.demo.exception.ResourceNotFoundException;
import com.project.demo.models.Food;
import com.project.demo.repos.FoodRepository;
import com.project.demo.service.CategoryService;
import com.project.demo.service.FoodService;
import com.project.demo.utils.StorageService;

@Service
	public class FoodServiceImpl implements FoodService {

	    @Autowired 
	    private StorageService storageService;
	    @Autowired 
	    private FoodRepository dao;
	    @Autowired
	    private CategoryService cdao;

	    @Override
	    public void addFood(Food food, MultipartFile pic) {
	        String photo = storageService.store(pic);
	        food.setPhoto(photo);
	        dao.save(food);
	    }

//	    @Override
//	    public void updateFood(Food food) {
//	        dao.save(food);
//	    }
	    @Override
	    public void updateFood(Food food, MultipartFile pic) {
	        if (pic != null && !pic.isEmpty()) {
	            String photo = storageService.store(pic);
	            food.setPhoto(photo);
	        }
	        dao.save(food);
	    }
	    
	    @Override
	    public void deleteFood(int id) {
	        dao.deleteById(id);
	    }


	    @Override
	    public List<Food> allFoods() {
	        return dao.findAll();
	    }
	    
	    public List<Food> searchFoods(String search) {
			return dao.findByFnameOrDescrContaining(search,search);
		}

	    @Override
	    public Food findFoodById(int fodid) {
	        return dao.findById(fodid)
	         .orElseThrow(() -> new ResourceNotFoundException("Food", "id", fodid));
	    }

	    @Override
	    public List<Food> categoryFoods(int catid) {
	        return dao.findByCategory(cdao.findById(catid));
	    }
	}


