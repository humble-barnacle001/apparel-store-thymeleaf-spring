package com.barnacle.apparel.views;

import com.barnacle.apparel.models.UserRepository;
import com.barnacle.apparel.service.MongoUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private MongoUserService userService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("")
    public String index(Model model) {
        Object o = userService.getCurrAuthentication().getPrincipal();
        if (o.getClass() == User.class) {
            User u = (User) o;
            boolean isAdmin = u.getAuthorities()
                    .stream().anyMatch(a -> "ROLE_ADMIN".equals(a.getAuthority()));
            if (isAdmin)
                return "redirect:/dashboard";
            com.barnacle.apparel.models.User user;
            user = userRepository.findByUsername(u.getUsername());
            if (user != null) {
                return user.getBuySaleCount() > user.getBuyNewCount()
                        ? "redirect:/current-sale"
                        : "redirect:/new-arrivals";
            }
        }
        return "index";
    }
}
