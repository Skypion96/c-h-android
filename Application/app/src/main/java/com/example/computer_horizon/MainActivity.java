package com.example.computer_horizon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.computer_horizon.models.Ordinateur;
import com.example.computer_horizon.services.OrdinateurRepositoryService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MAIN_ACTIVITY = "EXTRA_MAIN_ACTIVITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        OrdinateurRepositoryService.query().enqueue(new Callback<List<Ordinateur>>() {
            @Override
            public void onResponse(Call<List<Ordinateur>> call, Response<List<Ordinateur>> response) {
                Log.i("Ordinateur",response.body().toString());
            }

            @Override
            public void onFailure(Call<List<Ordinateur>> call, Throwable t) {
                Log.i("OrdinateurError",t.toString());
            }
        });

    }

    public void startConnexion(View view) {
        Intent intent = new Intent(MainActivity.this, Connexion.class);
        startActivity(intent);
    }

    public void startComposant(View view) {
        Intent intent = new Intent(MainActivity.this,Composant.class);
        startActivity(intent);
    }

}
