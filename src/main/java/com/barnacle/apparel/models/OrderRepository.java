package com.barnacle.apparel.models;

import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Order, String> {

    @Aggregation({ "{$match: {buyerId: ObjectId(?0)}},",
            "{$unset: '_class'},",
            "{$lookup: {from: 'item',localField: 'itemId',foreignField: '_id',as: 'item'}},",
            "{$unwind: {path: '$item',preserveNullAndEmptyArrays: false}}]" })
    AggregationResults<OrderItem> findOrderByBuyerId(String buyerId);
}
