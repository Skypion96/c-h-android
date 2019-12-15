package com.example.computer_horizon.models;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class DecodedToken implements Serializable {
    private String mail;


    public DecodedToken(String mail){
        this.mail = mail;
    }

    @NonNull
    @Override
    public String toString() {
        return mail;
    }

    public String getMail() {
        return mail;
    }
}
