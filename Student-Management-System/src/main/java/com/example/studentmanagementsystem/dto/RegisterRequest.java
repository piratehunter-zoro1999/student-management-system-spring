package com.example.studentmanagementsystem.dto;

public class RegisterRequest {
    private String username;
    private String password;

    public void setUsername(String userName) {
        this.username = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
