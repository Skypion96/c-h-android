package com.example.computer_horizon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.computer_horizon.models.DisqueD;
import com.example.computer_horizon.services.PanierCarteGraphiqueRepositoryService;
import com.example.computer_horizon.services.PanierDisqueDurRepositoryService;

import java.text.NumberFormat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PanierDDAll extends AppCompatActivity {

    TextView nom;
    TextView marque;
    TextView prix;
    TextView capacite;
    TextView ssd;
    TextView interne;
    DisqueD dd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panier_ddall);
        dd = (DisqueD) getIntent().getSerializableExtra(DisqueDListView.EXTRA_MAIN_ACTIVITY);
        NumberFormat nm = NumberFormat.getNumberInstance();
        nom = findViewById(R.id.nom_pandd);
        marque = findViewById(R.id.marque_pandd);
        prix = findViewById(R.id.prix_pandd);
        capacite = findViewById(R.id.capacite_pandd);
        ssd = findViewById(R.id.ssd_pandd);
        interne = findViewById(R.id.interne_pandd);


        nom.setText(dd.getNom());
        marque.setText(dd.getMarque());
        prix.setText(nm.format(dd.getPrix()));
        if(dd.isSsd()==true){
            ssd.setText("Il s'agit d'un SSD");
        }
        else{
            ssd.setText("Il ne s'agit pas d'un SSD");
        }
        capacite.setText(dd.getCapacite());
        if(dd.isInterne()==true){
            interne.setText("Il s'agit d'un disque dur interne");
        }
        else{
            interne.setText("Il ne s'agit pas d'un disque dur interne");
        }
    }

    public void supprimerItemDD(View view){
        PanierDisqueDurRepositoryService.delete(dd.getNom()).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });

        Intent intent = new Intent(PanierDDAll.this, PanierAffichage.class);
        startActivity(intent);
    }
}
