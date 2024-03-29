package com.barnacle.apparel.views;

import java.util.Optional;

import com.barnacle.apparel.models.Item;
import com.barnacle.apparel.models.ItemRepository;
import com.barnacle.apparel.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ItemController {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private OrderService orderService;

    @GetMapping("/current-sale")
    public String getSalePage(Model model) {
        model.addAttribute("sale", itemRepository.findSaleItems());
        return "sale";
    }

    @GetMapping("/new-arrivals")
    public String getArrivalsPage(Model model) {
        model.addAttribute("arrivals", itemRepository.findNewItems());
        return "arrivals";
    }

    @GetMapping("/buy/{itemId}")
    public String getUserDetailsById(@PathVariable("itemId") String id, Model model) {
        Optional<Item> item = orderService.addNewOrder(id);
        model.addAttribute("item", item);
        model.addAttribute("onSoldPage", true);
        return "itemsold";
    }

    @GetMapping("/orders")
    public String getOrders(Model model) {
        model.addAttribute("orders", orderService.getAllOrders());
        return "orders";
    }
}
