package com.example.computer_horizon.models;

public class UserConnected {

    private String token;

    public UserConnected(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
