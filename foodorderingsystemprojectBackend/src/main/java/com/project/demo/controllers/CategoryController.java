package com.project.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.demo.dtos.Response;
import com.project.demo.models.Category;
import com.project.demo.service.CategoryService;

@CrossOrigin
@RestController
@RequestMapping("/api/categories")
public class CategoryController {

	@Autowired private CategoryService cservice;
	
	@PostMapping
	public ResponseEntity<?> saveCategory(@RequestBody Category cat) {
		cservice.save(cat);
		return Response.success("Category saved");
	}
	
	@GetMapping
	public List<Category> listall(){
		return cservice.listAll();
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> deleteCategory(@PathVariable("id") int id){
		cservice.deleteCategory(id);
		return Response.success("Category deleted successfully");
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateCategory(@PathVariable("id") int id, @RequestBody Category cat) {
	    Category existingCategory = cservice.findById(id);
	    if (existingCategory == null) {
	        return ResponseEntity.notFound().build();
	    }
	    existingCategory.setCatname(cat.getCatname()); // Update category name
	    cservice.save(existingCategory); // Save updated category
	    return Response.success("Category updated");
	}

}
