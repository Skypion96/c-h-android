package com.example.computer_horizon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.computer_horizon.services.PanierCarteGraphiqueRepositoryService;
import com.example.computer_horizon.services.PanierOrdinateurRepositoryService;

import java.text.NumberFormat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PanierOrdiAll extends AppCompatActivity {

    TextView nom;
    TextView marque;
    TextView prix;
    TextView processeur;
    TextView carteG;
    TextView capacite;
    TextView memoireV;
    TextView ssd;
    TextView description;
    TextView capaciteSSD;
    com.example.computer_horizon.models.Ordinateur ordi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panier_ordi_all);
        ordi = (com.example.computer_horizon.models.Ordinateur)getIntent().getSerializableExtra(Ordinateur.EXTRA_MAIN_ACTIVITY);
        NumberFormat nm = NumberFormat.getNumberInstance();

        nom = findViewById(R.id.nom_panordi);
        marque = findViewById(R.id.marque_panordi);
        prix = findViewById(R.id.prix_panordi);
        processeur = findViewById(R.id.proc_panordi);
        carteG = findViewById(R.id.carteG_panordi);
        capacite = findViewById(R.id.capacite_panordi);
        memoireV = findViewById(R.id.memoireVOrdi_panordi);
        ssd = findViewById(R.id.ssd_panordi);
        description = findViewById(R.id.descr_panordi);
        capaciteSSD = findViewById(R.id.capSSD_panordi);


        nom.setText(ordi.getNom());
        marque.setText(ordi.getMarque());
        prix.setText(nm.format(ordi.getPrix()));
        processeur.setText(ordi.getNomProc());
        carteG.setText(ordi.getNomCG());
        capacite.setText(ordi.getCapacite());
        memoireV.setText(nm.format(ordi.getMemoireV()));
        if(ordi.isSsd()==true){
            ssd.setText("Il y a un SSD");
            capaciteSSD.setText(ordi.getCapaciteSsd());
        }
        else{
            ssd.setText("Il n'y a pas de SSD");
            capaciteSSD.setText("Il n'y a pas de SSD");
        }
        description.setText(ordi.getDescription());
    }

    public void supprimerItemOrdi(View view){
        PanierOrdinateurRepositoryService.delete(ordi.getNom()).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });

        Intent intent = new Intent(PanierOrdiAll.this, PanierAffichage.class);
        startActivity(intent);
    }
}
