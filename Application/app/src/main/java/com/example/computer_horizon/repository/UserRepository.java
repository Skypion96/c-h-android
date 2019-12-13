package com.example.computer_horizon.repository;

import com.example.computer_horizon.Configuration;
import com.example.computer_horizon.models.Authenticate;
import com.example.computer_horizon.models.UserConnected;
import com.example.computer_horizon.models.Utilisateur;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UserRepository {

    @GET(Configuration.API_USERACCOUNT)
    Call<List<Utilisateur>> query();
    @POST(Configuration.API_USERACCOUNT)
    Call<Utilisateur> post(@Body Utilisateur todo);
    @DELETE(Configuration.API_USERACCOUNT +"/{nom}")
    Call<Void> delete(@Path("nom")String nom);
    @PUT(Configuration.API_USERACCOUNT)
    Call<Void> put(@Body Utilisateur todo);
    @POST(Configuration.API_USERACCOUNT+"/authenticate")
    Call<UserConnected> authenticate(@Body Authenticate authenticate);
}
