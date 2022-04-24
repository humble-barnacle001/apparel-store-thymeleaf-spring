package com.barnacle.apparel.models;

public class OrderItem extends Order {
    private Item item;

    public Item getItem() {
        return item;
    }

    public OrderItem setItem(Item item) {
        this.item = item;
        return this;
    }
}
