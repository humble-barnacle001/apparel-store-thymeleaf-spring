package com.barnacle.apparel.models;

import org.springframework.security.core.GrantedAuthority;

public class UserRole implements GrantedAuthority {
    private Role role;

    @Override
    public String getAuthority() {
        return role.getName();
    }

    public Role getRole() {
        return role;
    }

    public UserRole setRole(Role role) {
        this.role = role;
        return this;
    }
}
