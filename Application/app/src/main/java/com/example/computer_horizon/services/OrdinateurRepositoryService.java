package com.example.computer_horizon.services;

import com.example.computer_horizon.Configuration;
import com.example.computer_horizon.models.Ordinateur;
import com.example.computer_horizon.repository.OrdinateurRepository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OrdinateurRepositoryService {

    private static final OrdinateurRepositoryService ourInstance = new OrdinateurRepositoryService();
    private OrdinateurRepository repository;
    public static OrdinateurRepositoryService getInstance() {
        return ourInstance;
    }

    private OrdinateurRepositoryService() {
        init();
    }

    public static Call<List<Ordinateur>> query(){
        return ourInstance.repository.query();
    }

    public static Call<Ordinateur> post(Ordinateur ordi){
        return ourInstance.repository.post(ordi);
    }

    public static Call<Void> delete(String nom){
        return ourInstance.repository.delete(nom);
    }

    public static Call<Void> put(Ordinateur ordi){
        return ourInstance.repository.put(ordi);
    }
    private void init(){
        repository = new Retrofit.Builder() //build
                .baseUrl(Configuration.BASE_URL_API)//build
                .addConverterFactory(GsonConverterFactory.create())//build
                .build()//retrofit instance
                .create(OrdinateurRepository.class);//TodoRepository instance
    }
}
