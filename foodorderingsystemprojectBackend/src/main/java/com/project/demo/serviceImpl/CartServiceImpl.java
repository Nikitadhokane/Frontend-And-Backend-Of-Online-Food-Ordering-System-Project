package com.project.demo.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.demo.exception.ResourceNotFoundException;
import com.project.demo.models.Cart;
import com.project.demo.models.Customer;
import com.project.demo.models.Food;
import com.project.demo.repos.CartRepository;
import com.project.demo.service.CartService;
import com.project.demo.service.CustomerService;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository repo;

    @Autowired
    private CustomerService customerService;

    @Override
    public void save(Cart cart) {
        // Set quantity to 1 if it's 0
        if (cart.getQty() == 0) {
            cart.setQty(1);
        }
        repo.save(cart);
    }
    
    @Override
    public List<Cart> findByUserId(int id) {
        Customer customer = customerService.findById(id);
        if (customer == null) {
            throw new ResourceNotFoundException("Customer not found with id: " + id);
        }
        return repo.findByCustomer(customer);
    }

    @Override
    public void updateQty(int cartId, int qty) {
        Cart cart = repo.findById(cartId).orElseThrow(() ->
            new ResourceNotFoundException("Cart item not found with id: " + cartId)
        );
        if (qty == 0) {
            qty = 1;
        }
        cart.setQty(qty);
        repo.save(cart);
    }

    @Override
    public void deleteItem(int id) {
        if (!repo.existsById(id)) {
            throw new ResourceNotFoundException("Cart item not found with id: " + id);
        }
        repo.deleteById(id);
    }

    @Override
    public boolean checkExist(Customer customer, Food food) {
        return repo.findByCustomerAndFood(customer, food) != null;
    }

    @Override
    public void clearCart(Customer cust) {
        List<Cart> carts = repo.findByCustomer(cust);
        if (carts.isEmpty()) {
            throw new ResourceNotFoundException("No items found in cart for customer: " + cust.getId());
        }
        repo.deleteAll(carts);
    }

	
	@Override
    public Cart findById(int id) {
        return repo.findById(id).orElse(null);
    }
}






































//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.project.demo.models.Cart;
//import com.project.demo.models.Customer;
//import com.project.demo.models.Food;
//import com.project.demo.repos.CartRepository;
//import com.project.demo.service.CartService;
//import com.project.demo.service.CustomerService;
//
//@Service
//public class CartServiceImpl implements CartService {
//
//  @Autowired
//  private CartRepository repo;
//
//  @Autowired
//  private CustomerService customerService;
//
//  @Override
//  public void save(Cart cart) {
//      repo.save(cart);
//  }
//
//  @Override
//  public List<Cart> findByUserId(int id) {
//      Customer customer = customerService.findById(id);
//      return repo.findByCustomer(customer);
//  }
//
//  @Override
//  public void updateQty(int cartId, int qty) {
//      Cart cart = repo.findById(cartId).orElse(null);
//      if (cart != null) {
//          cart.setQty(qty);
//          repo.save(cart);
//      }
//  }
//
//  @Override
//  public void deleteItem(int id) {
//      repo.deleteById(id);
//  }
//
//  @Override
//  public boolean checkExist(Customer customer, Food food) {
//      return repo.findByCustomerAndFood(customer, food) != null;
//  }
//
//  @Override
//  public void clearCart(Customer cust) {
//      repo.deleteAll(repo.findByCustomer(cust));
//  }
//}
//CartServiceImpl.java