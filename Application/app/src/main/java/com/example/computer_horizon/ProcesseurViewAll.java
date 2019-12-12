package com.example.computer_horizon;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.computer_horizon.models.Processeur;

import java.text.NumberFormat;

public class ProcesseurViewAll extends AppCompatActivity {

    TextView nom;
    TextView marque;
    TextView prix;
    TextView frequence;
    TextView nbCoeurs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_processeur_view_all);
        Processeur proc = (Processeur) getIntent().getSerializableExtra(ProcesseurListView.EXTRA_MAIN_ACTIVITY);
        NumberFormat nm = NumberFormat.getNumberInstance();
        Log.i("ordi",proc.getMarque());
        nom = findViewById(R.id.nom);
        marque = findViewById(R.id.marque);
        prix = findViewById(R.id.prix);
        frequence = findViewById(R.id.frequenceProc);
        nbCoeurs = findViewById(R.id.nbCoeurs);



        nom.setText(proc.getNom());
        marque.setText(proc.getMarque());
        prix.setText(nm.format(proc.getPrix()));
        frequence.setText(proc.getFrequence());
        nbCoeurs.setText(nm.format(proc.getNbCoeurs()));

    }
}
