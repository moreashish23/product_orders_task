package com.example.productorder.services;

import com.example.productorder.entity.*;
import com.example.productorder.repository.CartRepository;
import com.example.productorder.repository.OrderRepository;
import com.example.productorder.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserService userService;

    @Transactional
    public Order placeOrder(Long userId) {

        userService.getUserById(userId);

        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        Order order = new Order();
        order.setUser_Id(userId);

        double total = 0;

        for (CartItem ci : cart.getItems()) {

            Product p = productRepository.findById(ci.getProduct_id())
                    .orElseThrow(() -> new RuntimeException("Product not found"));


            p.setQuantity(p.getQuantity() - ci.getQuantity());
            productRepository.save(p);

            OrderItem oi = new OrderItem();
            oi.setProductId(p.getProductId());
            oi.setQuantity(ci.getQuantity());
            oi.setOrder(order);

            order.getItems().add(oi);
            total += p.getPrice() * ci.getQuantity();
        }

        order.setTotlal_Price(total);
        cart.getItems().clear();

        return orderRepository.save(order);
    }
}