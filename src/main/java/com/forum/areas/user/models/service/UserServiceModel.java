package com.forum.areas.user.models.service;

import com.forum.areas.roles.models.service.RoleServiceModel;

import java.util.Set;

public class UserServiceModel {
    private Long id;

    private String password;

    private String username;

    private String email;

    private Set<RoleServiceModel> roles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<RoleServiceModel> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleServiceModel> roles) {
        this.roles = roles;
    }
}