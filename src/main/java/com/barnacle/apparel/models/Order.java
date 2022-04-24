package com.barnacle.apparel.models;

import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

@Document
public class Order {
    @Id
    private String id;
    @Indexed
    @Field(targetType = FieldType.OBJECT_ID)
    private String buyerId;
    @Field(targetType = FieldType.OBJECT_ID)
    private String itemId;
    @CreatedDate
    private Date orderOn;

    public String getId() {
        return id;
    }

    public Order setId(String id) {
        this.id = id;
        return this;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public Order setBuyerId(String buyerId) {
        this.buyerId = buyerId;
        return this;
    }

    public String getItemId() {
        return itemId;
    }

    public Order setItemId(String itemId) {
        this.itemId = itemId;
        return this;
    }

    public Date getOrderOn() {
        return orderOn;
    }

    public Order setOrderOn(Date orderOn) {
        this.orderOn = orderOn;
        return this;
    }
}
