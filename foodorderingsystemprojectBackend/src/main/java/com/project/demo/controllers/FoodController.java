package com.project.demo.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.demo.dtos.FoodDTO;
import com.project.demo.dtos.Response;
import com.project.demo.models.Food;
import com.project.demo.service.FoodService;



@CrossOrigin
@RestController
@RequestMapping("/api/foods")
public class FoodController {

@Autowired FoodService bservice;
	
	@PostMapping
	public ResponseEntity<?> saveFood(FoodDTO dto) {
		System.out.println(dto);
		Food food=FoodDTO.toEntity(dto);
		bservice.addFood(food,dto.getPic());
		return Response.success(food);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<?> updateFood(
	    @PathVariable("id") int id,
	    @ModelAttribute FoodDTO dto
	) {
	    Food existingFood = bservice.findFoodById(id);
	    BeanUtils.copyProperties(dto, existingFood, "pic", "id");
	    bservice.updateFood(existingFood, dto.getPic());
	    return Response.success(existingFood);
	}

	
	@GetMapping("{id}")
	public ResponseEntity<?> findFood(@PathVariable("id")int id) {
		Food food=bservice.findFoodById(id);
		return Response.success(food);
	}

	
	@GetMapping
    public List<Food> findAllFoods(Optional<String> search) {
        List<Food> result = new ArrayList<>();
        if (search.isPresent()) {
            result.addAll(bservice.searchFoods(search.get()));
        } else {
            result.addAll(bservice.allFoods());
        }
        return result;
    }
	
	
	@GetMapping("cats")
    public List<Food> findByCategory(int catid) {
        return new ArrayList<>(bservice.categoryFoods(catid));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteFood(@PathVariable("id") int id) {
        bservice.deleteFood(id);
        return Response.status(HttpStatus.OK);
    }
}
