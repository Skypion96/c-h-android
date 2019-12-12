package com.example.computer_horizon;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.computer_horizon.models.CarteG;
import com.example.computer_horizon.models.Processeur;

import java.text.NumberFormat;

public class CarteGViewAll extends AppCompatActivity {

    TextView nom;
    TextView marque;
    TextView prix;
    TextView frequence;
    TextView memoireVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carte_gview_all);
        CarteG cg = (CarteG) getIntent().getSerializableExtra(CarteGListView.EXTRA_MAIN_ACTIVITY);
        NumberFormat nm = NumberFormat.getNumberInstance();
        Log.i("ordi",cg.getMarque());
        nom = findViewById(R.id.nom_cg);
        marque = findViewById(R.id.marque_cg);
        prix = findViewById(R.id.prix_cg);
        frequence = findViewById(R.id.frequencecg);
        memoireVideo = findViewById(R.id.memoireVideo_cg);



        nom.setText(cg.getNom());
        marque.setText(cg.getMarque());
        prix.setText(nm.format(cg.getPrix()));
        frequence.setText(cg.getFrequence());
        memoireVideo.setText(cg.getMemoireV());

    }
}
