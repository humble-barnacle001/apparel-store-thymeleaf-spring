package com.barnacle.apparel.models;

import java.util.Date;

import org.springframework.data.annotation.Id;

public class OrderItem {
    @Id
    private String id;
    private String buyerId;
    private String itemId;
    private Date orderOn;
    private Item item;
    private float cost;

    public String getId() {
        return id;
    }

    public OrderItem setId(String id) {
        this.id = id;
        return this;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public OrderItem setBuyerId(String buyerId) {
        this.buyerId = buyerId;
        return this;
    }

    public String getItemId() {
        return itemId;
    }

    public OrderItem setItemId(String itemId) {
        this.itemId = itemId;
        return this;
    }

    public Date getOrderOn() {
        return orderOn;
    }

    public OrderItem setOrderOn(Date orderOn) {
        this.orderOn = orderOn;
        return this;
    }

    public Item getItem() {
        return item;
    }

    public OrderItem setItem(Item item) {
        this.item = item;
        return this;
    }

    public Float getCost() {
        return cost;
    }

    public OrderItem setCost(Float cost) {
        this.cost = cost;
        return this;
    }
}
