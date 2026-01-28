package com.example.productorder.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name= "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long order_id;

    @Column(name = "User_Id")
    private Long user_Id;

    @Column(name = "Total_Price")
    private double totlal_Price;

    @OneToMany(mappedBy = "order", cascade= jakarta.persistence.CascadeType.ALL)
    private List<OrderItem> items= new ArrayList<>();

    public Order() {

    }

    public Order(Long order_id, Long user_Id, double totlal_Price, List<OrderItem> items) {
        this.order_id = order_id;
        this.user_Id = user_Id;
        this.totlal_Price = totlal_Price;
        this.items = items;
    }

    public Long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Long order_id) {
        this.order_id = order_id;
    }

    public Long getUser_Id() {
        return user_Id;
    }

    public void setUser_Id(Long user_Id) {
        this.user_Id = user_Id;
    }

    public double getTotlal_Price() {
        return totlal_Price;
    }

    public void setTotlal_Price(double totlal_Price) {
        this.totlal_Price = totlal_Price;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }
}
