package com.example.computer_horizon.repository;

import com.example.computer_horizon.Configuration;
import com.example.computer_horizon.models.Authenticate;
import com.example.computer_horizon.models.Utilisateur;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface AuthentificatonRepository {

    @GET(Configuration.API_USERACCOUNT+"/authenticate")
    retrofit2.Call<List<Utilisateur>> query();
    @POST(Configuration.API_USERACCOUNT+"/authenticate")
    retrofit2.Call<Utilisateur> post(@Body Utilisateur todo);
    @PUT(Configuration.API_USERACCOUNT+"/authenticate")
    Call<Void> put(@Body Utilisateur todo);

}
