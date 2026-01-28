package com.example.productorder.services;

import com.example.productorder.dto.CartItemDto;
import com.example.productorder.entity.Cart;
import com.example.productorder.entity.CartItem;
import com.example.productorder.entity.Product;
import com.example.productorder.repository.CartRepository;
import com.example.productorder.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserService userService;

    public Cart addToCart(Long userId, CartItemDto dto) {

        userService.getUserById(userId);

        Cart cart = cartRepository.findByUserId(userId)
                .orElseGet(() -> {
                    Cart c = new Cart();
                    c.setUserId(userId);
                    return cartRepository.save(c);
                });

        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));


        CartItem item = new CartItem();
        item.setProduct_id(dto.getProductId());
        item.setQuantity(dto.getQuantity());
        item.setCart(cart);

        cart.getItems().add(item);
        return cartRepository.save(cart);
    }
}
