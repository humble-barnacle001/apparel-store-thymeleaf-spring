package com.barnacle.apparel.models;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Item {

    @Id
    private String id;
    @Indexed
    private String name;
    private Float cost;
    private List<String> tags;
    private boolean isNew;
    private boolean isSale;
    private String imageId;
    private boolean isDeleted;
    private int buyCount;

    public String getId() {
        return id;
    }

    public Item setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Item setName(String name) {
        this.name = name;
        return this;
    }

    public Float getCost() {
        return cost;
    }

    public Item setCost(Float cost) {
        this.cost = cost;
        return this;
    }

    public List<String> getTags() {
        return tags;
    }

    public Item setTags(List<String> tags) {
        this.tags = tags;
        return this;
    }

    public boolean isNew() {
        return isNew;
    }

    public Item setNew(boolean isNew) {
        this.isNew = isNew;
        return this;
    }

    public boolean isSale() {
        return isSale;
    }

    public Item setSale(boolean isSale) {
        this.isSale = isSale;
        return this;
    }

    public String getImageId() {
        return imageId;
    }

    public Item setImageId(String imageId) {
        this.imageId = imageId;
        return this;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public Item setDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
        return this;
    }

    public int getBuyCount() {
        return buyCount;
    }

    public Item setBuyCount(int buyCount) {
        this.buyCount = buyCount;
        return this;
    }

    public Item incrementBuyCount() {
        this.buyCount++;
        return this;
    }

    public String toString() {
        return String.format(
                "Item{id=%s, name='%s', cost='%f',tags=['%s'], Is new='%b', On sale='%b', Image='%s'}",
                id, name, cost, String.join(", ", tags), isNew, isSale, imageId);
    }
}
