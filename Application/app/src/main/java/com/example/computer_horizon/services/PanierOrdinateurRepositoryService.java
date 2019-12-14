package com.example.computer_horizon.services;

import com.example.computer_horizon.Configuration;
import com.example.computer_horizon.models.PanierOrdinateur;
import com.example.computer_horizon.repository.PanierOrdinateurRepository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PanierOrdinateurRepositoryService {

    private static final PanierOrdinateurRepositoryService ourInstance = new PanierOrdinateurRepositoryService();
    private PanierOrdinateurRepository repository;
    public static PanierOrdinateurRepositoryService getInstance() {
        return ourInstance;
    }

    private PanierOrdinateurRepositoryService() {
        init();
    }

    public static Call<List<PanierOrdinateur>> query(){
        return ourInstance.repository.query();
    }

    public static Call<PanierOrdinateur> post(PanierOrdinateur dd){
        return ourInstance.repository.post(dd);
    }

    public static Call<Void> delete(String nom){
        return ourInstance.repository.delete(nom);
    }

    public static Call<Void> put(PanierOrdinateur dd){
        return ourInstance.repository.put(dd);
    }
    private void init(){
        repository = new Retrofit.Builder() //build
                .baseUrl(Configuration.BASE_URL_API)//build
                .addConverterFactory(GsonConverterFactory.create())//build
                .build()//retrofit instance
                .create(PanierOrdinateurRepository.class);//TodoRepository instance
    }
}