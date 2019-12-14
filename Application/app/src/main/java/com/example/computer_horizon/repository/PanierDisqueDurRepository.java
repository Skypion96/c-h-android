package com.example.computer_horizon.repository;

import com.example.computer_horizon.Configuration;
import com.example.computer_horizon.models.DisqueD;
import com.example.computer_horizon.models.Panier_disqueDur;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface PanierDisqueDurRepository {

    @GET(Configuration.API_PANIERDD)
    Call<List<Panier_disqueDur>> query();
    @POST(Configuration.API_PANIERDD)
    Call<Panier_disqueDur> post(@Body Panier_disqueDur todo);
    @DELETE(Configuration.API_PANIERDD +"/{nom}")
    Call<Void> delete(@Path("nom")String nom);
    @PUT(Configuration.API_PANIERDD)
    Call<Void> put(@Body Panier_disqueDur todo);
}
