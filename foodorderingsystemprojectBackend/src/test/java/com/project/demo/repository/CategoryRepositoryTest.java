package com.project.demo.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import com.project.demo.models.Category;
import com.project.demo.repos.CategoryRepository;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void testCreateCategory() {
        // Create a new category
        Category category = new Category();
        category.setCatname("Test Category");
        
        // Save the category in the repository
        Category savedCategory = categoryRepository.save(category);

        // Check if the category is saved successfully with a generated ID
        assertNotNull(savedCategory.getId());
    }

    @Test
    public void testUpdateCategory() {
        // Create a new category
        Category category = new Category();
        category.setCatname("Test Category");
        
        // Save the category in the repository
        Category savedCategory = categoryRepository.save(category);

        // Check if the category is saved successfully with a generated ID
        assertNotNull(savedCategory.getId());

        // Update the category name
        savedCategory.setCatname("Updated Category");
        categoryRepository.save(savedCategory);

        // Retrieve the updated category
        Category updatedCategory = categoryRepository.findById(savedCategory.getId()).orElse(null);

        // Check if the category name is updated
        assertNotNull(updatedCategory);
        assertEquals("Updated Category", updatedCategory.getCatname());
    }
    
    @Test
    public void testSaveCategory() {
        // Create a new category
        Category category = new Category();
        category.setCatname("Test Category");

        // Save the category
        Category savedCategory = categoryRepository.save(category);

        // Check if the category is saved successfully with a generated ID
        assertNotNull(savedCategory.getId());
    }



    @Test
    public void testFindCategoryById() {
        // Create a category
        Category category = new Category();
        category.setCatname("Test Category");
        Category savedCategory = categoryRepository.save(category);

        // Find category by ID
        Category foundCategory = categoryRepository.findById(savedCategory.getId()).orElse(null);

        // Check if the correct category is retrieved
        assertNotNull(foundCategory);
        assertEquals(savedCategory.getCatname(), foundCategory.getCatname());
    }

    @Test
    public void testDeleteCategory() {
        // Create a category
        Category category = new Category();
        category.setCatname("Test Category");
        Category savedCategory = categoryRepository.save(category);
        int categoryId = savedCategory.getId();

        // Delete the category
        categoryRepository.deleteById(categoryId);

        // Try to find the deleted category
        Category deletedCategory = categoryRepository.findById(categoryId).orElse(null);

        // Check if the category is deleted
        assertNull(deletedCategory);
    }
}