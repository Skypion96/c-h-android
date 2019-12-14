package com.example.computer_horizon.repository;

import com.example.computer_horizon.Configuration;
import com.example.computer_horizon.models.PanierProcesseur;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface PanierProcesseurRepository {

    @GET(Configuration.API_PANIERPROC)
    Call<List<PanierProcesseur>> query();
    @POST(Configuration.API_PANIERPROC)
    Call<PanierProcesseur> post(@Body PanierProcesseur todo);
    @DELETE(Configuration.API_PANIERPROC +"/{nom}")
    Call<Void> delete(@Path("nom")String nom);
    @PUT(Configuration.API_PANIERPROC)
    Call<Void> put(@Body PanierProcesseur todo);
}
