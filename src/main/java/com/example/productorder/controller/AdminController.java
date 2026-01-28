package com.example.productorder.controller;

import com.example.productorder.entity.Product;
import com.example.productorder.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ProductService productService;

    @PostMapping("/products")
    public Product add(@RequestBody Product p) {
        return productService.addProduct(p);
    }

    @PutMapping("/products/{id}")
    public Product update(@PathVariable Long id,
                          @RequestBody Product p) {
        return productService.updateProduct(id, p);
    }
}


