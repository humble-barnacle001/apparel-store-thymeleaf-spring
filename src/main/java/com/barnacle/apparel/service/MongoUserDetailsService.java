package com.barnacle.apparel.service;

import java.util.HashSet;
import java.util.Set;

import com.barnacle.apparel.models.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MongoUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.barnacle.apparel.models.User user = userRepository.findByUsername(username);
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

        if (user == null)
            throw new UsernameNotFoundException(username);

        user.getAuthorities()
                .forEach(role -> {
                    grantedAuthorities.add(new SimpleGrantedAuthority(role.getRole().getName()));
                });

        return new User(user.getUsername(), user.getPassword(), grantedAuthorities);
    }

}
