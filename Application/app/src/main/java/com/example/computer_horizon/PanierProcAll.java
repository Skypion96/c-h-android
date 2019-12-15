package com.example.computer_horizon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.computer_horizon.models.Processeur;
import com.example.computer_horizon.services.PanierCarteGraphiqueRepositoryService;
import com.example.computer_horizon.services.PanierProcesseurRepositoryService;

import java.text.NumberFormat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PanierProcAll extends AppCompatActivity {

    TextView nom;
    TextView marque;
    TextView prix;
    TextView frequence;
    TextView nbCoeurs;
    Processeur proc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panier_proc_all);
        proc = (Processeur) getIntent().getSerializableExtra(ProcesseurListView.EXTRA_MAIN_ACTIVITY);
        NumberFormat nm = NumberFormat.getNumberInstance();
        nom = findViewById(R.id.nom_panproc);
        marque = findViewById(R.id.marque_panproc);
        prix = findViewById(R.id.prix_panproc);
        frequence = findViewById(R.id.frequencepanproc);
        nbCoeurs = findViewById(R.id.nbCoeurs_panproc);


        nom.setText(proc.getNom());
        marque.setText(proc.getMarque());
        prix.setText(nm.format(proc.getPrix()));
        frequence.setText(proc.getFrequence());
        nbCoeurs.setText(nm.format(proc.getNbCoeurs()));
    }

    public void supprimerItemProc(View view){
        PanierProcesseurRepositoryService.delete(proc.getNom()).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });

        Intent intent = new Intent(PanierProcAll.this, PanierAffichage.class);
        startActivity(intent);
    }
}
