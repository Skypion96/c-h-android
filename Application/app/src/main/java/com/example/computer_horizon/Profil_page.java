package com.example.computer_horizon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.auth0.android.jwt.JWT;
import com.example.computer_horizon.models.Utilisateur;
import com.example.computer_horizon.services.UserRepositoryService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Profil_page extends AppCompatActivity {

    List<Utilisateur> users;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil_page);

        UserRepositoryService.query().enqueue(new Callback<List<Utilisateur>>() {
            @Override
            public void onResponse(Call<List<Utilisateur>> call, Response<List<Utilisateur>> response) {
                users = response.body();
            }

            @Override
            public void onFailure(Call<List<Utilisateur>> call, Throwable t) {

            }
        });



    }

    public void affichageProfil(List<Utilisateur> user){
        
    }

    public void startModif(View view){
        Intent intent = new Intent(Profil_page.this, Modif_Profil.class);
        startActivity(intent);
    }
}
