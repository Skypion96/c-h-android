package com.example.computer_horizon.repository;

import com.example.computer_horizon.Configuration;
import com.example.computer_horizon.models.DisqueD;
import com.example.computer_horizon.models.Panier_processeur;

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
    Call<List<Panier_processeur>> query();
    @POST(Configuration.API_PANIERPROC)
    Call<Panier_processeur> post(@Body Panier_processeur todo);
    @DELETE(Configuration.API_PANIERPROC +"/{nom}")
    Call<Void> delete(@Path("nom")String nom);
    @PUT(Configuration.API_PANIERPROC)
    Call<Void> put(@Body Panier_processeur todo);
}
