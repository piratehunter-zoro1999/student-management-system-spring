package com.example.studentmanagementsystem.dto;

import com.example.studentmanagementsystem.model.Role;

public class CurrentUserResponse {
    private String username;
    private Role role;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public Role getRole() {
        return role;
    }
}
