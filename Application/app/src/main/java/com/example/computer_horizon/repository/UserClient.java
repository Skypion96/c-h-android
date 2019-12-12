package com.example.computer_horizon.repository;

import com.example.computer_horizon.models.Login;
import com.example.computer_horizon.models.Utilisateur;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserClient {

    @POST("login")
    Call<Utilisateur> login(@Body Login login);

}
