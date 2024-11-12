package com.project.demo.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.project.demo.models.Customer;
import com.project.demo.repos.CustomerRepository;

@SpringBootTest
public class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void testCreateCustomer() {
        // Test creating a new customer
        Customer customer = new Customer();
        customer.setName("Neha ");
        customer.setCity("New York");
        customer.setUserid("neha@gmail.com");
        customer.setPwd("password");
        customer.setPhone("1234567890");
        customer.setGender("Female");

        Customer savedCustomer = customerRepository.save(customer);

        assertNotNull(savedCustomer.getId());
    }

//    @Test
//    public void testFindByUserid() {
//        // Test finding a customer by userid
//        Customer customer = new Customer();
//        customer.setName("isha patil");
//        customer.setCity("pune");
//        customer.setUserid("ishapatil@gmail.com");
//        customer.setPwd("password");
//        customer.setPhone("9876543210");
//        customer.setGender("Female");
//
//        customerRepository.save(customer);
//
//        Customer foundCustomer = customerRepository.findByUserid("ishapatil@gmail.com");
//
//        assertNotNull(foundCustomer);
//        assertEquals("isha patil", foundCustomer.getName());
//    }

    @Test
    public void testUpdateCustomer() {
        // Test updating customer details
        Customer customer = new Customer();
        customer.setName("Sam Smith");
        customer.setCity("Chicago");
        customer.setUserid("samsmith@gmail.com");
        customer.setPwd("pass123");
        customer.setPhone("5555555555");
        customer.setGender("Male");

        Customer savedCustomer = customerRepository.save(customer);

        assertNotNull(savedCustomer.getId());

        // Update customer details
        savedCustomer.setPhone("1111111111");
        Customer updatedCustomer = customerRepository.save(savedCustomer);

        assertEquals("1111111111", updatedCustomer.getPhone());
    }

    @Test
    public void testDeleteCustomer() {
        // Test deleting a customer
        Customer customer = new Customer();
        customer.setName("Rekha patil");
        customer.setCity("Pune");
        customer.setUserid("rekhapatil@gmail.com");
        customer.setPwd("rekha123");
        customer.setPhone("9999999999");
        customer.setGender("Female");

        Customer savedCustomer = customerRepository.save(customer);
        int customerId = savedCustomer.getId();

        assertNotNull(customerRepository.findById(customerId));

        // Delete the customer
        customerRepository.deleteById(customerId);

        assertTrue(customerRepository.findById(customerId).isEmpty());
    }
}