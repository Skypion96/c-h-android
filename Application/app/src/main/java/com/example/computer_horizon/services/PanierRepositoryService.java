package com.example.computer_horizon.services;

import com.example.computer_horizon.Configuration;
import com.example.computer_horizon.models.Panier;
import com.example.computer_horizon.models.PanierProcesseur;
import com.example.computer_horizon.repository.PanierProcesseurRepository;
import com.example.computer_horizon.repository.PanierRepository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PanierRepositoryService {
    private static final PanierRepositoryService ourInstance = new PanierRepositoryService();
    private PanierRepository repository;
    public static PanierRepositoryService getInstance() {
        return ourInstance;
    }

    private PanierRepositoryService() {
        init();
    }

    public static Call<List<Panier>> query(){
        return ourInstance.repository.query();
    }

    public static Call<Panier> post(Panier dd){
        return ourInstance.repository.post(dd);
    }

    private void init(){
        repository = new Retrofit.Builder() //build
                .baseUrl(Configuration.BASE_URL_API)//build
                .addConverterFactory(GsonConverterFactory.create())//build
                .build()//retrofit instance
                .create(PanierRepository.class);//TodoRepository instance
    }
}