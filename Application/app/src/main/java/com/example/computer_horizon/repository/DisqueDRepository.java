package com.example.computer_horizon.repository;

import com.example.computer_horizon.Configuration;
import com.example.computer_horizon.models.DisqueD;
import com.example.computer_horizon.models.Ordinateur;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface DisqueDRepository {

    @GET(Configuration.API_DISQUED)
    Call<List<DisqueD>> query();
    @POST(Configuration.API_DISQUED)
    Call<DisqueD> post(@Body DisqueD todo);
    @DELETE(Configuration.API_DISQUED +"/{nom}")
    Call<Void> delete(@Path("nom")String nom);
    @PUT(Configuration.API_DISQUED)
    Call<Void> put(@Body DisqueD todo);
}
