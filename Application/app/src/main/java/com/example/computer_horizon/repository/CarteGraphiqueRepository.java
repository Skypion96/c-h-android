package com.example.computer_horizon.repository;

import com.example.computer_horizon.Configuration;
import com.example.computer_horizon.models.CarteG;
import com.example.computer_horizon.models.Ordinateur;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface CarteGraphiqueRepository {

    @GET(Configuration.API_CARTEG)
    Call<List<CarteG>> query();
    @POST(Configuration.API_CARTEG)
    Call<CarteG> post(@Body CarteG todo);
    @DELETE(Configuration.API_CARTEG +"/{nom}")
    Call<Void> delete(@Path("nom")String nom);
    @PUT(Configuration.API_CARTEG)
    Call<Void> put(@Body CarteG todo);
}
