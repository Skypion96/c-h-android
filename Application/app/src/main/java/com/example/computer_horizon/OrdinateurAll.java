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

        nom = findViewById(R.id.nom_ordi);
        marque = findViewById(R.id.marque_ordi);
        prix = findViewById(R.id.prix_ordi);
        processeur = findViewById(R.id.proc_ordi);
        carteG = findViewById(R.id.carteG_ordi);
        capacite = findViewById(R.id.capacite_ordi);
        memoireV = findViewById(R.id.memoireVOrdi_ordi);
        ssd = findViewById(R.id.ssd_ordi);
        description = findViewById(R.id.descr_ordi);
        capaciteSSD = findViewById(R.id.capSSD_ordi);


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
