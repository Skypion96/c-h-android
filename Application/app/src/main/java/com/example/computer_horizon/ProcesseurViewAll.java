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
        nom = findViewById(R.id.nom_proc);
        marque = findViewById(R.id.marque_proc);
        prix = findViewById(R.id.prix_proc);
        frequence = findViewById(R.id.frequenceProc);
        nbCoeurs = findViewById(R.id.nbCoeurs_proc);



        nom.setText(proc.getNom());
        marque.setText(proc.getMarque());
        prix.setText(nm.format(proc.getPrix()));
        frequence.setText(proc.getFrequence());
        nbCoeurs.setText(nm.format(proc.getNbCoeurs()));

    }
}
