package com.example.computer_horizon.services;

import com.example.computer_horizon.Configuration;
import com.example.computer_horizon.models.DisqueD;
import com.example.computer_horizon.models.Ordinateur;
import com.example.computer_horizon.repository.DisqueDRepository;
import com.example.computer_horizon.repository.OrdinateurRepository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DisqueDRepositoryService {

    private static final DisqueDRepositoryService ourInstance = new DisqueDRepositoryService();
    private DisqueDRepository repository;
    public static DisqueDRepositoryService getInstance() {
        return ourInstance;
    }

    private DisqueDRepositoryService() {
        init();
    }

    public static Call<List<DisqueD>> query(){
        return ourInstance.repository.query();
    }

    public static Call<DisqueD> post(DisqueD dd){
        return ourInstance.repository.post(dd);
    }

    public static Call<Void> delete(String nom){
        return ourInstance.repository.delete(nom);
    }

    public static Call<Void> put(DisqueD dd){
        return ourInstance.repository.put(dd);
    }
    private void init(){
        repository = new Retrofit.Builder() //build
                .baseUrl(Configuration.BASE_URL_API)//build
                .addConverterFactory(GsonConverterFactory.create())//build
                .build()//retrofit instance
                .create(DisqueDRepository.class);//TodoRepository instance
    }
}
