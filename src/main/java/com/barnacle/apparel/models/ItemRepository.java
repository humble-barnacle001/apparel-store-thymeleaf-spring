package com.barnacle.apparel.models;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ItemRepository extends MongoRepository<Item, String> {

    List<Item> findByName(@Param("name") String name);

    @Query("{'isSale': true}")
    List<Item> findSaleItems();

    @Query("{'isNew': true}")
    List<Item> findNewItems();
}
