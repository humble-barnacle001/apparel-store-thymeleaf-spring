package com.barnacle.apparel.service;

import java.util.Date;
import java.util.Optional;

import com.barnacle.apparel.models.Item;
import com.barnacle.apparel.models.ItemRepository;
import com.barnacle.apparel.models.Order;
import com.barnacle.apparel.models.OrderRepository;
import com.barnacle.apparel.models.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MongoUserService userService;

    public Optional<Item> addNewOrder(String itemId) {
        Optional<Item> oItem = itemRepository.findById(itemId);
        try {
            User u = (User) userService.getCurrAuthentication().getPrincipal();
            if (u != null && oItem.isPresent()) {
                com.barnacle.apparel.models.User user;
                user = userRepository.findByUsername(u.getUsername());
                Item item = oItem.get();
                if (user != null && !item.isDeleted()) {
                    if (item.isNew())
                        user.incBuyNewCount();
                    if (item.isSale())
                        user.incBuySaleCount();
                    itemRepository.save(item.incrementBuyCount());
                    userRepository.save(user);
                    orderRepository.save(
                            new Order()
                                    .setBuyerId(user.getId())
                                    .setItemId(item.getId())
                                    .setOrderOn(new Date()));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return oItem;
    }
}
