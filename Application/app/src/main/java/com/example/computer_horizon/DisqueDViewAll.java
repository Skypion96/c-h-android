package com.example.computer_horizon;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.computer_horizon.models.CarteG;
import com.example.computer_horizon.models.DisqueD;

import java.text.NumberFormat;

public class DisqueDViewAll extends AppCompatActivity {

    TextView nom;
    TextView marque;
    TextView prix;
    TextView capacite;
    TextView ssd;
    TextView interne;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disque_dview_all);
        DisqueD dd = (DisqueD) getIntent().getSerializableExtra(DisqueDListView.EXTRA_MAIN_ACTIVITY);
        NumberFormat nm = NumberFormat.getNumberInstance();
        nom = findViewById(R.id.nom_dd);
        marque = findViewById(R.id.marque_dd);
        prix = findViewById(R.id.prix_dd);
        capacite = findViewById(R.id.capacite_dd);
        ssd = findViewById(R.id.ssd_dd);
        interne = findViewById(R.id.interne_dd);


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
}
