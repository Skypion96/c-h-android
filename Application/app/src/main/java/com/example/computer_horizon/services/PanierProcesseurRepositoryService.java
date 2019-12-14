package com.example.computer_horizon.services;

import com.example.computer_horizon.Configuration;
import com.example.computer_horizon.models.Panier_carteGraphique;
import com.example.computer_horizon.models.Panier_processeur;
import com.example.computer_horizon.repository.PanierCarteGraphiqueRepository;
import com.example.computer_horizon.repository.PanierProcesseurRepository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PanierProcesseurRepositoryService {

    private static final PanierProcesseurRepositoryService ourInstance = new PanierProcesseurRepositoryService();
    private PanierProcesseurRepository repository;
    public static PanierProcesseurRepositoryService getInstance() {
        return ourInstance;
    }

    private PanierProcesseurRepositoryService() {
        init();
    }

    public static Call<List<Panier_processeur>> query(){
        return ourInstance.repository.query();
    }

    public static Call<Panier_processeur> post(Panier_processeur dd){
        return ourInstance.repository.post(dd);
    }

    public static Call<Void> delete(String nom){
        return ourInstance.repository.delete(nom);
    }

    public static Call<Void> put(Panier_processeur dd){
        return ourInstance.repository.put(dd);
    }
    private void init(){
        repository = new Retrofit.Builder() //build
                .baseUrl(Configuration.BASE_URL_API)//build
                .addConverterFactory(GsonConverterFactory.create())//build
                .build()//retrofit instance
                .create(PanierProcesseurRepository.class);//TodoRepository instance
    }
}