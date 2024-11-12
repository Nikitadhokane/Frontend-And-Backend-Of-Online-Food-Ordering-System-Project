package com.project.demo.repository;

//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//import java.util.Optional;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.jdbc.Sql;
//
//import com.project.demo.dtos.OrderDetailsDTO;
//import com.project.demo.models.Food;
//import com.project.demo.models.Order;
//import com.project.demo.models.OrderDetails;
//import com.project.demo.repos.OrderDetailsRepository;
//import com.project.demo.repos.OrderRepository;
//
//@SpringBootTest
//public class OrderDetailsRepositoryTest {
//
//    @Autowired
//    private OrderDetailsRepository orderDetailsRepository;
//
//    @Autowired
//    private OrderRepository orderRepository;
//
//    @Test
//    public void createOrderDetails_ValidData_ReturnsSavedEntity() {
//        // Given
//        Order order = orderRepository.findById(1).orElse(null);
//        Food food = new Food();
//        OrderDetailsDTO orderDetailsDTO = new OrderDetailsDTO();
//        orderDetailsDTO.setFood(food);
//        orderDetailsDTO.setQty(2);
//
//        // When
//        OrderDetails savedOrderDetails = orderDetailsRepository.createOrderDetails(orderDetailsDTO, order);
//
//        // Then
//        assertEquals(food, savedOrderDetails.getFood());
//        assertEquals(2, savedOrderDetails.getQty());
//    }
//
//    @Test
//    
//    public void saveOrderDetails_ValidData_ReturnsSavedEntity() {
//        // Given
//        Order order = orderRepository.findById(1).orElse(null);
//        OrderDetails orderDetails = new OrderDetails();
//        orderDetails.setOrder(order);
//
//        // When
//        OrderDetails savedOrderDetails = orderDetailsRepository.save(orderDetails);
//
//        // Then
//        assertEquals(order, savedOrderDetails.getOrder());
//    }
//
//    @Test
//    public void updateOrderDetails_ValidData_ReturnsUpdatedEntity() {
//        // Given
//        OrderDetails orderDetails = orderDetailsRepository.findById(1).orElse(null);
//        assertNotNull(orderDetails);
//        int newQuantity = 5;
//        orderDetails.setQty(newQuantity);
//
//        // When
//        OrderDetails updatedOrderDetails = orderDetailsRepository.save(orderDetails);
//
//        // Then
//        assertEquals(newQuantity, updatedOrderDetails.getQty());
//    }
//
//    @Test
//    public void findOrderDetailsById_ValidId_ReturnsOptionalOrderDetails() {
//        // Given
//        int orderDetailsId = 1;
//
//        // When
//        Optional<OrderDetails> foundOrderDetails = orderDetailsRepository.findById(orderDetailsId);
//
//        // Then
//        assertTrue(foundOrderDetails.isPresent());
//        assertEquals(orderDetailsId, foundOrderDetails.get().getId());
//    }
//
//    // Add more tests for other methods as needed
//}