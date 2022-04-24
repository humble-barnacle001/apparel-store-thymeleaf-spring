package com.barnacle.apparel.models;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.userdetails.UserDetails;

@Document
public class User implements UserDetails {
    @Id
    private String id;
    private String name;
    @Indexed(unique = true)
    private String username;
    private String password;
    private Set<UserRole> userRoles;
    private int buyNewCount;
    private int buySaleCount;

    public String getId() {
        return id;
    }

    public User setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    @Override
    public Set<UserRole> getAuthorities() {
        return userRoles;
    }

    public User addAuthorities(UserRole role) {
        if (userRoles == null)
            userRoles = new HashSet<>();
        userRoles.add(role);
        return this;
    }

    public int getBuyNewCount() {
        return buyNewCount;
    }

    public User setBuyNewCount(int buyNewCount) {
        this.buyNewCount = buyNewCount;
        return this;
    }

    public User incBuyNewCount() {
        this.buyNewCount++;
        return this;
    }

    public int getBuySaleCount() {
        return buySaleCount;
    }

    public User setBuySaleCount(int buySaleCount) {
        this.buySaleCount = buySaleCount;
        return this;
    }

    public User incBuySaleCount() {
        this.buySaleCount++;
        return this;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        User user = (User) o;
        return Objects.equals(username, user.username) && Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }
}
