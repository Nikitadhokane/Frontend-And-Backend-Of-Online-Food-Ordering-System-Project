package com.project.demo.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.project.demo.dtos.OrderDetailsDTO;
import com.project.demo.dtos.OrderResponseDTO;
import com.project.demo.dtos.PlaceOrderDTO;
import com.project.demo.dtos.Response;
import com.project.demo.models.Cart;
import com.project.demo.models.Customer;
import com.project.demo.models.Order;
import com.project.demo.models.OrderDetails;
import com.project.demo.service.*;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin
public class OrdersController {

    @Autowired OrderService orderService;
    @Autowired CustomerService customerService;
    @Autowired AddressService addressService;
    @Autowired PaymentService paymentService;
    @Autowired OrderDetailsService orderDetailsService;
    @Autowired FoodService foodService;
    @Autowired CartService cartservice;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody PlaceOrderDTO dto) {
        Order order = new Order();
        Customer customer = customerService.findById(dto.getCustomerid());
        order.setCustomer(customer);
        order.setOrderDate(LocalDate.now()); // Set current date and time

        Order savedOrder = orderService.placeOrder(order);

        for (Cart cart : cartservice.findByUserId(dto.getCustomerid())) {
            OrderDetails od = new OrderDetails();
            od.setOrder(savedOrder);
            od.setQty(cart.getQty());
            od.setFood(cart.getFood());
            orderDetailsService.saveOrderDetails(od);
        }
        cartservice.clearCart(customer);
        return Response.success("Order placed");
    }

    @GetMapping("/confirm/{id}")
    public ResponseEntity<?> confirmOrder(@PathVariable("id") int id) {
        orderService.confirm(id);
        return Response.success("Confirmed");
    }

    @GetMapping
    public List<Order> findAllOrders(Optional<Integer> custid) {
        List<Order> result;
        if (custid.isPresent()) {
            Customer customer = customerService.findById(custid.get());
            result = orderService.getCustomerOrders(customer);
        } else {
            result = orderService.getAllOrders();
        }
        return result;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findOrderById(@PathVariable("id") int id) {
        Order order = orderService.findById(id);
        List<OrderDetails> details = orderDetailsService.findByOrder(order);
        List<OrderDetailsDTO> detailsdto = new ArrayList<>();
        details.forEach(od -> {
            OrderDetailsDTO dto = OrderDetailsDTO.fromEntity(od);
            detailsdto.add(dto);
        });
        OrderResponseDTO result = new OrderResponseDTO();
        result.setOrder(order);
        result.setDetails(detailsdto);
        return Response.success(result);
    }
}
