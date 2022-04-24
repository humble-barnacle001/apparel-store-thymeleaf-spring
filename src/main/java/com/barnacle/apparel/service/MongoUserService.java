package com.barnacle.apparel.service;

import com.barnacle.apparel.error.UserAlreadyExistsException;
import com.barnacle.apparel.models.Role;
import com.barnacle.apparel.models.User;
import com.barnacle.apparel.models.UserRepository;
import com.barnacle.apparel.models.UserRole;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MongoUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerNewUserAccount(User user) throws UserAlreadyExistsException {
        if (usernameExists(user.getUsername())) {
            throw new UserAlreadyExistsException(
                    "There is already an account with the email address: " + user.getUsername());
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.addAuthorities(new UserRole().setRole(new Role().setName("ROLE_USER")));

        return userRepository.save(user);
    }

    private boolean usernameExists(String username) {
        return userRepository.findByUsername(username) != null;
    }

    public Authentication getCurrAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
