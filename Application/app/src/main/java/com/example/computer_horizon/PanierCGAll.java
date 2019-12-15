package com.example.computer_horizon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.computer_horizon.models.CarteG;
import com.example.computer_horizon.services.PanierCarteGraphiqueRepositoryService;

import java.text.NumberFormat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PanierCGAll extends AppCompatActivity {

    TextView nom;
    TextView marque;
    TextView prix;
    TextView frequence;
    TextView memoireVideo;
    CarteG cg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panier_cgall);
        cg = (CarteG) getIntent().getSerializableExtra(PanierCarteGList.EXTRA_MAIN_ACTIVITY);
        NumberFormat nm = NumberFormat.getNumberInstance();
        nom = findViewById(R.id.nom_pancg);
        marque = findViewById(R.id.marque_pancg);
        prix = findViewById(R.id.prix_pancg);
        frequence = findViewById(R.id.frequencepancg);
        memoireVideo = findViewById(R.id.memoireVideo_pancg);



        nom.setText(cg.getNom());
        marque.setText(cg.getMarque());
        prix.setText(nm.format(cg.getPrix()));
        frequence.setText(cg.getFrequence());
        memoireVideo.setText(cg.getMemoireV());

    }

    public void supprimerItem(View view){
        PanierCarteGraphiqueRepositoryService.delete(cg.getNom()).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });

        Intent intent = new Intent(PanierCGAll.this, PanierAffichage.class);
        startActivity(intent);
    }
}
