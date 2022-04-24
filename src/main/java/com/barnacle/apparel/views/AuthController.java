package com.barnacle.apparel.views;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.barnacle.apparel.error.UserAlreadyExistsException;
import com.barnacle.apparel.models.User;
import com.barnacle.apparel.service.MongoUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    @Autowired
    private MongoUserService userService;

    private boolean isNotLoggedIn() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (authentication == null ||
                authentication instanceof AnonymousAuthenticationToken);
    }

    @GetMapping("/auth")
    public String getAuthPage() {
        return isNotLoggedIn() ? "auth" : "redirect:/";
    }

    @PostMapping("/register")
    public String register(HttpServletRequest request, @RequestParam("name") String name,
            @RequestParam("username") String userName, @RequestParam("password") String password, Model model) {

        String customAlert = null;
        if (!isNotLoggedIn())
            return "redirect:/";
        try {
            User newUser = new User()
                    .setName(name)
                    .setUsername(userName)
                    .setPassword(password);
            userService.registerNewUserAccount(newUser);
            request.login(userName, password);

            return "redirect:/auth";

        } catch (UserAlreadyExistsException e) {
            customAlert = e.getMessage();
        } catch (ServletException se) {
            System.out.println("Error occurred: " + se.getMessage());
            se.printStackTrace();
            customAlert = "Server error occured";
        }
        model.addAttribute("alertMessage", customAlert);
        return "auth";
    }
}
