package com.example.computer_horizon.repository;

import com.example.computer_horizon.Configuration;
import com.example.computer_horizon.models.DisqueD;
import com.example.computer_horizon.models.Panier_carteGraphique;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface PanierCarteGraphiqueRepository {

    @GET(Configuration.API_PANIERCG)
    Call<List<Panier_carteGraphique>> query();
    @POST(Configuration.API_PANIERCG)
    Call<Panier_carteGraphique> post(@Body Panier_carteGraphique todo);
    @DELETE(Configuration.API_PANIERCG +"/{nom}")
    Call<Void> delete(@Path("nom")String nom);
    @PUT(Configuration.API_PANIERCG)
    Call<Void> put(@Body Panier_carteGraphique todo);
}
