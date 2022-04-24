package com.barnacle.apparel.views;

import java.util.List;

import com.barnacle.apparel.models.Item;
import com.barnacle.apparel.models.ItemRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private ItemRepository iRepository;

    @GetMapping
    public String getSearchPage(@RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "range", required = false) String rangeString, Model model) {
        if (name != null && rangeString != null) {
            try {
                int range = Integer.parseInt(rangeString);
                model.addAttribute("name", name);
                model.addAttribute("range", rangeString);
                model.addAttribute("isSearch", true);
                List<Item> sItems = iRepository.findByNameBelowCost(name, range);
                model.addAttribute("searchResults", sItems);
            } catch (NumberFormatException nfe) {
                System.out.println(nfe);
                return "redirect:/search";
            }
        } else {
            model.addAttribute("minRange", 100);
            model.addAttribute("maxRange", 50000);
        }
        return "search";
    }
}
