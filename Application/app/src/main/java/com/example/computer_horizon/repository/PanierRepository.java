package com.example.computer_horizon.repository;

import com.example.computer_horizon.Configuration;
import com.example.computer_horizon.models.Panier;
import com.example.computer_horizon.models.PanierProcesseur;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface PanierRepository {

    @GET(Configuration.API_PANIER)
    Call<List<Panier>> query();
    @POST(Configuration.API_PANIER)
    Call<Panier> post(@Body Panier todo);

}
