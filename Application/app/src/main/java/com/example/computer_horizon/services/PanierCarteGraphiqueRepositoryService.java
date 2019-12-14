package com.example.computer_horizon.services;

import com.example.computer_horizon.Configuration;
import com.example.computer_horizon.models.PanierCarteGraphique;
import com.example.computer_horizon.repository.PanierCarteGraphiqueRepository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PanierCarteGraphiqueRepositoryService {

    private static final PanierCarteGraphiqueRepositoryService ourInstance = new PanierCarteGraphiqueRepositoryService();
    private PanierCarteGraphiqueRepository repository;
    public static PanierCarteGraphiqueRepositoryService getInstance() {
        return ourInstance;
    }

    private PanierCarteGraphiqueRepositoryService() {
        init();
    }

    public static Call<List<PanierCarteGraphique>> query(){
        return ourInstance.repository.query();
    }

    public static Call<PanierCarteGraphique> post(PanierCarteGraphique dd){
        return ourInstance.repository.post(dd);
    }

    public static Call<Void> delete(String nom){
        return ourInstance.repository.delete(nom);
    }

    public static Call<Void> put(PanierCarteGraphique dd){
        return ourInstance.repository.put(dd);
    }
    private void init(){
        repository = new Retrofit.Builder() //build
                .baseUrl(Configuration.BASE_URL_API)//build
                .addConverterFactory(GsonConverterFactory.create())//build
                .build()//retrofit instance
                .create(PanierCarteGraphiqueRepository.class);//TodoRepository instance
    }
}