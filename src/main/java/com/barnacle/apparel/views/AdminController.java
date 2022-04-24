package com.barnacle.apparel.views;

import com.barnacle.apparel.models.ItemRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @Autowired
    private ItemRepository itemRepository;

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("all", itemRepository.findAll());
        return "dashboard";
    }
}
