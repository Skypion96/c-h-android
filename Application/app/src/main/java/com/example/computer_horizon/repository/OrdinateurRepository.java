package com.example.computer_horizon.repository;

import com.example.computer_horizon.Configuration;
import com.example.computer_horizon.models.Ordinateur;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface OrdinateurRepository {

    @GET(Configuration.API_ORDINATEUR)
    Call<List<Ordinateur>> query();
    @POST(Configuration.API_ORDINATEUR)
    Call<Ordinateur> post(@Body Ordinateur todo);
    @DELETE(Configuration.API_ORDINATEUR +"/{nom}")
    Call<Void> delete(@Path("nom")String nom);
    @PUT(Configuration.API_ORDINATEUR)
    Call<Void> put(@Body Ordinateur todo);
}
