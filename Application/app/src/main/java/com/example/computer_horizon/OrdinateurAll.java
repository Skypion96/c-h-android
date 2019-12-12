package com.example.computer_horizon;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.text.NumberFormat;


public class OrdinateurAll extends AppCompatActivity {

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordinateur_all);
         com.example.computer_horizon.models.Ordinateur ordi = (com.example.computer_horizon.models.Ordinateur)getIntent().getSerializableExtra(Ordinateur.EXTRA_MAIN_ACTIVITY);
        NumberFormat nm = NumberFormat.getNumberInstance();

        nom = findViewById(R.id.nom);
        marque = findViewById(R.id.marque);
        prix = findViewById(R.id.prix);
        processeur = findViewById(R.id.frequence);
        carteG = findViewById(R.id.nbCoeurs);
        capacite = findViewById(R.id.capacite);
        memoireV = findViewById(R.id.memoireV);
        ssd = findViewById(R.id.ssd);
        description = findViewById(R.id.descr);
        capaciteSSD = findViewById(R.id.capSSD);


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
}
