package com.project.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.project.demo.dtos.LoginDTO;
import com.project.demo.dtos.Response;
import com.project.demo.models.Customer;
import com.project.demo.service.CustomerService;

@CrossOrigin
@RestController
@RequestMapping("/api/customers")
public class CustomerController {
	
	@Autowired CustomerService customerService;

	@PostMapping("/register")
    public ResponseEntity<?> registerCustomer(@RequestBody Customer cust) {
        try {
            customerService.registerCustomer(cust);
            return ResponseEntity.ok("Registered successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Registration failed: " + e.getMessage());
        }
    }

	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody Customer cust) {		
		System.out.println(cust);
		customerService.registerCustomer(cust);
		return Response.success(cust);
	}
	
	@GetMapping
	public List<Customer> findAllCustomers() {
		return customerService.allCustomers();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findCustomerById(@PathVariable("id") int id) {
		Customer result = customerService.findById(id);
		return Response.success(result);
	}
	
	@PostMapping("/validate")
	public ResponseEntity<?> validateUser(@RequestBody LoginDTO dto) {
		System.out.println(dto);
		Customer user=customerService.validate(dto.getUserid(),dto.getPwd());
		if(user!=null)
			return Response.success(user);
		else
			return Response.status(HttpStatus.NOT_FOUND);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<?> updateProfile(@RequestBody Customer cust,@PathVariable("id") int id) {
		customerService.updateProfile(cust);
		return Response.status(HttpStatus.OK);
	}
	
//	@DeleteMapping("/{id}")
//    public ResponseEntity<?> deleteUser(@PathVariable("id") int id) {
//        try {
//            customerService.deleteUser(id);
//            return ResponseEntity.ok().build();
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    }
	
	 @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deleteCustomer(@PathVariable int id) {
		 customerService.deleteUser(id);
	        return ResponseEntity.noContent().build();
	    }


}
