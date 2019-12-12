package com.example.computer_horizon.services;

import com.example.computer_horizon.Configuration;
import com.example.computer_horizon.models.Ordinateur;
import com.example.computer_horizon.models.Processeur;
import com.example.computer_horizon.repository.OrdinateurRepository;
import com.example.computer_horizon.repository.ProcesseurRepository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProcesseurRepositoryService {

    private static final ProcesseurRepositoryService ourInstance = new ProcesseurRepositoryService();
    private ProcesseurRepository repository;
    public static ProcesseurRepositoryService getInstance() {
        return ourInstance;
    }

    private ProcesseurRepositoryService() {
        init();
    }

    public static Call<List<Processeur>> query(){
        return ourInstance.repository.query();
    }

    public static Call<Processeur> post(Processeur proc){
        return ourInstance.repository.post(proc);
    }

    public static Call<Void> delete(String nom){
        return ourInstance.repository.delete(nom);
    }

    public static Call<Void> put(Processeur proc){
        return ourInstance.repository.put(proc);
    }
    private void init(){
        repository = new Retrofit.Builder() //build
                .baseUrl(Configuration.BASE_URL_API)//build
                .addConverterFactory(GsonConverterFactory.create())//build
                .build()//retrofit instance
                .create(ProcesseurRepository.class);//TodoRepository instance
    }
}
