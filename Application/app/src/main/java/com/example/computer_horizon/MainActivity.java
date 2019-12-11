package com.example.computer_horizon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.computer_horizon.models.Ordinateur;
import com.example.computer_horizon.services.OrdinateurRepositoryService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MAIN_ACTIVITY = "EXTRA_MAIN_ACTIVITY";
    private Ordinateur o =new Ordinateur("tt","tt",3.25,"tt","tt","55",5,false,"ttt",5,"tt","pppp",5.00);

    private List<Ordinateur> ordis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }
    public void startConnexion(View view) {
        Intent intent = new Intent(MainActivity.this, Connexion.class);
        startActivity(intent);
    }

    public void startComposant(View view) {
        Intent intent = new Intent(MainActivity.this,Composant.class);
        startActivity(intent);
    }

    public void loadOrdi(){
        ordis = new ArrayList<>();
        OrdinateurRepositoryService.query();
    }

}
