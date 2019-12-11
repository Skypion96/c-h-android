package com.example.computer_horizon.services;

import com.example.computer_horizon.Configuration;
import com.example.computer_horizon.models.Ordinateur;
import com.example.computer_horizon.models.Utilisateur;
import com.example.computer_horizon.repository.OrdinateurRepository;
import com.example.computer_horizon.repository.UserRepository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserRepositoryService {

    private static final UserRepositoryService ourInstance = new UserRepositoryService();
    private UserRepository repository;
    public static UserRepositoryService getInstance() {
        return ourInstance;
    }

    private UserRepositoryService() {
        init();
    }

    public static Call<List<Utilisateur>> query(){
        return ourInstance.repository.query();
    }

    public static Call<Utilisateur> post(Utilisateur user){
        return ourInstance.repository.post(user);
    }

    public static Call<Void> put(Utilisateur user){
        return ourInstance.repository.put(user);
    }
    private void init(){
        repository = new Retrofit.Builder() //build
                .baseUrl(Configuration.BASE_URL_API)//build
                .addConverterFactory(GsonConverterFactory.create())//build
                .build()//retrofit instance
                .create(UserRepository.class);//TodoRepository instance
    }
}
