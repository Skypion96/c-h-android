package com.example.computer_horizon.services;

import com.example.computer_horizon.Configuration;
import com.example.computer_horizon.models.Panier_carteGraphique;
import com.example.computer_horizon.models.Panier_disqueDur;
import com.example.computer_horizon.repository.PanierCarteGraphiqueRepository;
import com.example.computer_horizon.repository.PanierDisqueDurRepository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PanierDisqueDurRepositoryService {

    private static final PanierDisqueDurRepositoryService ourInstance = new PanierDisqueDurRepositoryService();
    private PanierDisqueDurRepository repository;
    public static PanierDisqueDurRepositoryService getInstance() {
        return ourInstance;
    }

    private PanierDisqueDurRepositoryService() {
        init();
    }

    public static Call<List<Panier_disqueDur>> query(){
        return ourInstance.repository.query();
    }

    public static Call<Panier_disqueDur> post(Panier_disqueDur dd){
        return ourInstance.repository.post(dd);
    }

    public static Call<Void> delete(String nom){
        return ourInstance.repository.delete(nom);
    }

    public static Call<Void> put(Panier_disqueDur dd){
        return ourInstance.repository.put(dd);
    }
    private void init(){
        repository = new Retrofit.Builder() //build
                .baseUrl(Configuration.BASE_URL_API)//build
                .addConverterFactory(GsonConverterFactory.create())//build
                .build()//retrofit instance
                .create(PanierDisqueDurRepository.class);//TodoRepository instance
    }
}