package com.example.computer_horizon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.computer_horizon.services.OrdinateurRepositoryService;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MAIN_ACTIVITY = "EXTRA_MAIN_ACTIVITY";
    private com.example.computer_horizon.models.Ordinateur o =new com.example.computer_horizon.models.Ordinateur("tt","tt",3.25,"tt","tt","55",5,false,"ttt",5,"tt","pppp",5.00);

    private List<com.example.computer_horizon.models.Ordinateur> ordis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }
    public void startConnexion(View view) {
        Intent intent = new Intent(MainActivity.this, Connexion.class);
        startActivity(intent);
    }

    public void startOrdinateur(View view) {
        Intent intent = new Intent(MainActivity.this, Ordinateur.class);
        startActivity(intent);
    }

    public void startProcesseur(View view) {
        Intent intent = new Intent(MainActivity.this, ProcesseurListView.class);
        startActivity(intent);
    }


}
