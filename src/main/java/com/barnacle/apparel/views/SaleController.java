package com.barnacle.apparel.views;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SaleController {

    @GetMapping("/current-sale")
    public String getSalePage() {
        return "sale";
    }

    @GetMapping("/new-arrivals")
    public String getArrivalsPage() {
        return "arrivals";
    }
}
