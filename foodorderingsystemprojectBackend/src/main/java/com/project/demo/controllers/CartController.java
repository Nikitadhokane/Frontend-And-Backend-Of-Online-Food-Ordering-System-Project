package com.project.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.demo.dtos.Response;
import com.project.demo.exception.ResourceNotFoundException;
import com.project.demo.models.Cart;
import com.project.demo.service.CartService;

@CrossOrigin
@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired private CartService service;
    
    @PostMapping
    public ResponseEntity<?> saveItem(@RequestBody Cart wl) {
        System.out.println(wl);
        if (service.checkExist(wl.getCustomer(), wl.getFood())) {
            return ResponseEntity.badRequest().body("Item already exists");
        }
        service.save(wl);
        return Response.success("Item saved into cart");
    }
    
    @GetMapping("/updateqty")
    public ResponseEntity<?> updateQty(@RequestParam("cartid") int cartid, @RequestParam("qty") int qty) {
        Cart cart = service.findById(cartid);
        if (cart == null) {
            throw new ResourceNotFoundException("Cart item not found with id: " + cartid);
        }
        service.updateQty(cartid, qty);
        return Response.success("Quantity updated");
    }
    
    @GetMapping
    public List<Cart> listall(@RequestParam("custid") int custid) {
        return service.findByUserId(custid);
    }

    
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteItem(@PathVariable("id") int id) {
        Cart cart = service.findById(id);
        if (cart == null) {
            throw new ResourceNotFoundException("Cart item not found with id: " + id);
        }
        service.deleteItem(id);
        return Response.success("Item deleted successfully");
    }
}
    
    
    
    
    
    
    
//    @GetMapping("/updateqty")
//    public ResponseEntity<?> updateQty(@RequestParam("cartid") int cartid, @RequestParam("qty") int qty) {
//        service.updateQty(cartid, qty);
//        return Response.success("Quantity updated");
//    }
//    
//    @GetMapping
//    public List<Cart> listall(@RequestParam("custid") int custid) {
//        return service.findByUserId(custid);
//    }
//    
//    @DeleteMapping("{id}")
//    public ResponseEntity<?> deleteItem(@PathVariable("id") int id) {
//        service.deleteItem(id);
//        return Response.success("Item deleted successfully");
//    }
//}



































//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.project.demo.dtos.Response;
//import com.project.demo.models.Cart;
//import com.project.demo.service.CartService;
//
//@CrossOrigin
//@RestController
//@RequestMapping("/api/cart")
//public class CartController {
//
//	@Autowired private CartService service;
//	
//	@PostMapping
//	public ResponseEntity<?> saveItem(@RequestBody Cart wl) {
//		System.out.println(wl);
//		if(service.checkExist(wl.getCustomer(), wl.getFood())) {
//			return ResponseEntity.badRequest().body("Item already exists");
//		}
//		service.save(wl);
//		return Response.success("Item saved into cart");
//	}
//	
//	
//	@GetMapping("updateqty")
//	public ResponseEntity<?> updateQty(int cartid,int qty){
//		service.updateQty(cartid, qty);
//		return Response.success("quantity updated");
//	}
//	
//	@GetMapping
//	public List<Cart> listall(int custid){
//		return service.findByUserId(custid);
//	}
//	
//	@DeleteMapping("{id}")
//	public ResponseEntity<?> deleteItem(@PathVariable("id") int id){
//		service.deleteItem(id);
//		return Response.success("item deleted successfully");
//	}
//}

//CartController.java

