package com.example.computer_horizon.models;

import retrofit2.Call;

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
