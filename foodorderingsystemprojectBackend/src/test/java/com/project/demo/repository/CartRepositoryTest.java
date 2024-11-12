package com.project.demo.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.project.demo.models.Cart;
import com.project.demo.models.Customer;
import com.project.demo.models.Food;
import com.project.demo.repos.CartRepository;
import com.project.demo.repos.CustomerRepository;
import com.project.demo.repos.FoodRepository;

@SpringBootTest
@Transactional
public class CartRepositoryTest {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private FoodRepository foodRepository;

    @Test
    public void testSaveCart() {
        // Create a customer
        Customer customer = new Customer();
        customer.setName("John Doe");
        customer.setCity("New York");
        customer.setUserid("john.doe");
        customer.setPwd("testpassword");
        customer.setPhone("1234567890");
        customer.setGender("Male");
        Customer savedCustomer = customerRepository.save(customer);

        // Create a food item
        Food food = new Food();
        food.setFname("Burger");
        food.setDescr("Delicious burger");
        // Set other food details
        Food savedFood = foodRepository.save(food);

        // Create a cart
        Cart cart = new Cart();
        cart.setCustomer(savedCustomer);
        cart.setFood(savedFood);
        cart.setQty(2);

        // Save the cart
        Cart savedCart = cartRepository.save(cart);

        // Check if the cart is saved successfully with a generated ID
        assertNotNull(savedCart.getId());
    }

    @Test
    public void testFindByCustomer() {
        // Create a customer
        Customer customer = new Customer();
        customer.setName("Rohan sharma");
        customer.setCity("Mumbai");
        customer.setUserid("rohansharma@gmail.com");
        customer.setPwd("testpassword");
        customer.setPhone("9876543210");
        customer.setGender("male");
        Customer savedCustomer = customerRepository.save(customer);

        // Create a food item
        Food food = new Food();
        food.setFname("Pizza");
        food.setDescr("Delicious pizza");
        // Set other food details
        Food savedFood = foodRepository.save(food);

        // Create carts for the customer
        Cart cart1 = new Cart();
        cart1.setCustomer(savedCustomer);
        cart1.setFood(savedFood);
        cart1.setQty(2);
        cartRepository.save(cart1);

        Cart cart2 = new Cart();
        cart2.setCustomer(savedCustomer);
        cart2.setFood(savedFood);
        cart2.setQty(3);
        cartRepository.save(cart2);

        // Find carts by customer
        List<Cart> carts = cartRepository.findByCustomer(savedCustomer);

        assertNotNull(carts);
        assertEquals(2, carts.size());
    }

    @Test
    public void testFindByCustomerAndFood() {
        // Create a customer
        Customer customer = new Customer();
        customer.setName("Yuvi Singh");
        customer.setCity("Chicago");
        customer.setUserid("yuvi@gmail.com");
        customer.setPwd("yuvi123");
        customer.setPhone("5555555555");
        customer.setGender("Male");
        Customer savedCustomer = customerRepository.save(customer);

        // Create food items
        Food food1 = new Food();
        food1.setFname("Sushi");
        food1.setDescr("Delicious sushi");
        // Set other food details
        Food savedFood1 = foodRepository.save(food1);

        Food food2 = new Food();
        food2.setFname("Ramen");
        food2.setDescr("Tasty ramen");
        // Set other food details
        Food savedFood2 = foodRepository.save(food2);

        // Create carts for the customer
        Cart cart1 = new Cart();
        cart1.setCustomer(savedCustomer);
        cart1.setFood(savedFood1);
        cart1.setQty(2);
        cartRepository.save(cart1);

        Cart cart2 = new Cart();
        cart2.setCustomer(savedCustomer);
        cart2.setFood(savedFood2);
        cart2.setQty(3);
        cartRepository.save(cart2);

        // Find cart by customer and food
        Cart foundCart = cartRepository.findByCustomerAndFood(savedCustomer, savedFood2);

        assertNotNull(foundCart);
        assertEquals(savedCustomer, foundCart.getCustomer());
        assertEquals(savedFood2, foundCart.getFood());
        assertEquals(3, foundCart.getQty());
    }

    @Test
    public void testDeleteByCustomer() {
        // Create a customer
        Customer customer = new Customer();
        customer.setName("Alice Smith");
        customer.setCity("San Francisco");
        customer.setUserid("alice.smith");
        customer.setPwd("testpassword");
        customer.setPhone("1111111111");
        customer.setGender("Female");
        Customer savedCustomer = customerRepository.save(customer);

        // Create a food item
        Food food = new Food();
        food.setFname("Pasta");
        food.setDescr("Delicious pasta");
        // Set other food details
        Food savedFood = foodRepository.save(food);

        // Create carts for the customer
        Cart cart1 = new Cart();
        cart1.setCustomer(savedCustomer);
        cart1.setFood(savedFood);
        cart1.setQty(2);
        cartRepository.save(cart1);

        Cart cart2 = new Cart();
        cart2.setCustomer(savedCustomer);
        cart2.setFood(savedFood);
        cart2.setQty(3);
        cartRepository.save(cart2);

        // Delete carts by customer
        cartRepository.deleteByCustomer(savedCustomer);

        // Find carts after deletion
        List<Cart> carts = cartRepository.findByCustomer(savedCustomer);

        assertNotNull(carts);
        assertEquals(0, carts.size());
    }
}