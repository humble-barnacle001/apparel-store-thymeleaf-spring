package com.barnacle.apparel.views;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("")
    public String index(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object o = authentication.getPrincipal();
        if (o.getClass() == User.class) {
            User user = (User) o;
            boolean isAdmin = user.getAuthorities()
                    .stream().anyMatch(a -> "ROLE_ADMIN".equals(a.getAuthority()));
            if (isAdmin)
                return "redirect:/dashboard";
            // String username = user.getUsername();
            // TODO: FETCH PREFERENCES AND REDIRECT
            return "redirect:/new-arrivals";
        }
        return "index";
    }
}
