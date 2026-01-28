package com.example.productorder.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long c_Id;

    @Column(name = "User_Id")
    private Long userId;

    @OneToMany(mappedBy = "cart", cascade = jakarta.persistence.CascadeType.ALL)
    private List<CartItem> items = new ArrayList<>();

    public Cart() {

    }

    public Cart(Long c_Id, Long userId, List<CartItem> items) {
        this.c_Id = c_Id;
        this.userId = userId;
        this.items = items;
    }

    public Long getC_Id() {
        return c_Id;
    }

    public void setC_Id(Long c_Id) {
        this.c_Id = c_Id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }
}
