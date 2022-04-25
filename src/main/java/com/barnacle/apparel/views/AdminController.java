package com.barnacle.apparel.views;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import com.barnacle.apparel.models.Item;
import com.barnacle.apparel.models.ItemRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminController {

    @Autowired
    private ItemRepository itemRepository;

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("all", itemRepository.findAll());
        return "dashboard";
    }

    @GetMapping("/item/delete/{itemId}")
    public String deleteItem(@PathVariable("itemId") String id, Model model, HttpServletResponse response) {
        String message = "Successfully Deleted", icon = "check-circle-fill", type = "success";
        try {
            Optional<Item> oItem = itemRepository.findById(id);
            if (oItem.isPresent()) {
                Item item = oItem.get();
                itemRepository.save(item.setDeleted(true));
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                message = "No such item with id " + id + " exists";
                icon = "exclamation-triangle-fill";
                type = "danger";
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            message = "Server error";
            icon = "exclamation-triangle-fill";
            type = "danger";
        }
        return String.format("fragments/alert::alert(message='%s', icon='%s', type='%s')", message, icon, type);
    }

    @PostMapping("/item")
    public String addItem(
            @RequestParam("name") String name,
            @RequestParam("cost") String costString,
            @RequestParam(name = "tags", defaultValue = "") String tagString,
            @RequestParam(name = "isNew", defaultValue = "off") String isNewString,
            @RequestParam(name = "isSale", defaultValue = "off") String isSaleString,
            @RequestParam(name = "imageId", defaultValue = "") String imageId) {

        try {
            float cost = Float.parseFloat(costString);
            List<String> tags = Arrays.asList(tagString.split(","));
            boolean isNew = isNewString.equals("on");
            boolean isSale = isSaleString.equals("on");
            itemRepository.save(
                    new Item()
                            .setName(name.trim())
                            .setCost(cost)
                            .setTags(tags)
                            .setNew(isNew)
                            .setSale(isSale)
                            .setImageId(imageId.trim()));
        } catch (Exception e) {
            System.out.println("EXCEPTION IN ADD NEW ITEM");
            e.printStackTrace();
        }
        return "redirect:/dashboard";
    }
}
