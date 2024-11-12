package com.project.demo.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.project.demo.models.Category;
import com.project.demo.models.Food;
import com.project.demo.repos.FoodRepository;

@SpringBootTest
@Transactional
class FoodRepositoryTest {

    @Autowired
    private FoodRepository foodRepository;
    @Test
    public void testCreateFood() {
        
        Category category = new Category(1);
        Food newFood = new Food();

        
        Food savedFood = foodRepository.save(newFood);

        
        assertNotNull(savedFood);
        assertEquals("Margherita Pizza", savedFood.getFname());
        assertEquals(category, savedFood.getCategory());
    }

//    @Test
//    void testFindByCategory() {
//        // Given
//        Category category = new Category(0, "Italian");
//        Food food1 = new Food(0, "Pizza", "Delicious pizza", category, 12, "pizza.jpg");
//        Food food2 = new Food(0, "Pasta", "Tasty pasta", category, 15, "pasta.jpg");
//
//        foodRepository.saveAll(List.of(food1, food2));
//
//        // When
//        List<Food> result = foodRepository.findByCategory(category);
//
//        // Then
//        assertEquals(2, result.size());
//    }

//    @Test
//    void testFindByfnameOrDescrContaining() {
//        // Given
//        Category category = new Category(0, "Fast Food");
//        foodRepository.save(new Food(0, "Burger", "Juicy burger", category, 10, "burger.jpg"));
//        foodRepository.save(new Food(0, "Salad", "Healthy salad", new Category(0, "Vegetarian"), 8, "salad.jpg"));
//
//        // When
//        List<Food> result = foodRepository.findByfnameOrDescrContaining("Burger", "burger");
//
//        // Then
//        assertEquals(1, result.size());
//    }

    @Test
    void testSaveFood() {
        
        Food food = new Food();        
        Food savedFood = foodRepository.save(food);
        assertEquals(food, savedFood);
    }


}