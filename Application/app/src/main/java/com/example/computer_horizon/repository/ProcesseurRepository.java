package com.example.computer_horizon.repository;

import com.example.computer_horizon.Configuration;
import com.example.computer_horizon.models.Ordinateur;
import com.example.computer_horizon.models.Processeur;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ProcesseurRepository {

    @GET(Configuration.API_PROCESSEUR)
    Call<List<Processeur>> query();
    @POST(Configuration.API_PROCESSEUR)
    Call<Processeur> post(@Body Processeur proc);
    @DELETE(Configuration.API_PROCESSEUR +"/{nom}")
    Call<Void> delete(@Path("nom")String nom);
    @PUT(Configuration.API_PROCESSEUR)
    Call<Void> put(@Body Processeur proc);
}
