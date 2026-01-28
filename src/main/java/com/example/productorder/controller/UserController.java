package com.example.productorder.controller;


import com.example.productorder.dto.CartItemDto;
import com.example.productorder.entity.Cart;
import com.example.productorder.entity.Order;
import com.example.productorder.entity.Product;
import com.example.productorder.entity.User;
import com.example.productorder.services.CartService;
import com.example.productorder.services.OrderService;
import com.example.productorder.services.ProductService;
import com.example.productorder.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CartService cartService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;



    @GetMapping("/products")
    public List<Product> viewProducts() {
        return productService.allProducts();
    }

    @PostMapping("/cart/{userId}")
    public Cart addToCart(@PathVariable Long userId,
                          @RequestBody CartItemDto dto) {
        return cartService.addToCart(userId, dto);
    }

    @PostMapping("/order/{userId}")
    public Order placeOrder(@PathVariable Long userId) {
        return orderService.placeOrder(userId);
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
}
