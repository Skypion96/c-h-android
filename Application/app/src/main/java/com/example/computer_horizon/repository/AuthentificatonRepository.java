package com.example.computer_horizon.repository;

import android.telecom.Call;

import com.example.computer_horizon.Configuration;
import com.example.computer_horizon.models.Authenticate;
import com.example.computer_horizon.models.Utilisateur;

import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthentificatonRepository {
  /*  @POST(Configuration.API_USERACCOUNT +"/authenticate")
    Call<Utilisateur> authenticate(@Body Authenticate authenticate);*/
}
