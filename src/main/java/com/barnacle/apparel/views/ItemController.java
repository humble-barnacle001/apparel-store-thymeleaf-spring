package com.barnacle.apparel.views;

import com.barnacle.apparel.models.ItemRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ItemController {

    @Autowired
    private ItemRepository itemRepository;

    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("all", itemRepository.findAll());
        return "index";
    }

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
}
