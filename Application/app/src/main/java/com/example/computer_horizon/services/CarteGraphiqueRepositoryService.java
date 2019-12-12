package com.example.computer_horizon.services;

import com.example.computer_horizon.Configuration;
import com.example.computer_horizon.models.CarteG;
import com.example.computer_horizon.models.Ordinateur;
import com.example.computer_horizon.repository.CarteGraphiqueRepository;
import com.example.computer_horizon.repository.OrdinateurRepository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CarteGraphiqueRepositoryService {

    private static final CarteGraphiqueRepositoryService ourInstance = new CarteGraphiqueRepositoryService();
    private CarteGraphiqueRepository repository;
    public static CarteGraphiqueRepositoryService getInstance() {
        return ourInstance;
    }

    private CarteGraphiqueRepositoryService() {
        init();
    }

    public static Call<List<CarteG>> query(){
        return ourInstance.repository.query();
    }

    public static Call<CarteG> post(CarteG carteG){
        return ourInstance.repository.post(carteG);
    }

    public static Call<Void> delete(String nom){
        return ourInstance.repository.delete(nom);
    }

    public static Call<Void> put(CarteG carteG){
        return ourInstance.repository.put(carteG);
    }
    private void init(){
        repository = new Retrofit.Builder() //build
                .baseUrl(Configuration.BASE_URL_API)//build
                .addConverterFactory(GsonConverterFactory.create())//build
                .build()//retrofit instance
                .create(CarteGraphiqueRepository.class);//TodoRepository instance
    }
}
