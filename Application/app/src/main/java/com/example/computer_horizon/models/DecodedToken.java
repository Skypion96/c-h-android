package com.example.computer_horizon.models;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class DecodedToken implements Serializable {
    private String unique_name;


    public DecodedToken(String mail){
        this.unique_name = mail;
    }

    @NonNull
    @Override
    public String toString() {
        return unique_name;
    }

    public String getUnique_name() {
        return unique_name;
    }
}
