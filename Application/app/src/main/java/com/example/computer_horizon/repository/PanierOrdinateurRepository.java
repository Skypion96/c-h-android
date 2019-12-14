package com.example.computer_horizon.repository;

import com.example.computer_horizon.Configuration;
import com.example.computer_horizon.models.DisqueD;
import com.example.computer_horizon.models.Panier_Ordinateur;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface PanierOrdinateurRepository {

    @GET(Configuration.API_PANIERORDI)
    Call<List<Panier_Ordinateur>> query();
    @POST(Configuration.API_PANIERORDI)
    Call<Panier_Ordinateur> post(@Body Panier_Ordinateur todo);
    @DELETE(Configuration.API_PANIERORDI +"/{nom}")
    Call<Void> delete(@Path("nom")String nom);
    @PUT(Configuration.API_PANIERORDI)
    Call<Void> put(@Body Panier_Ordinateur todo);
}
