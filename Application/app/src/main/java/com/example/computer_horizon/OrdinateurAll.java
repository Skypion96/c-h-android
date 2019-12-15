package com.example.computer_horizon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.computer_horizon.models.Panier;
import com.example.computer_horizon.models.PanierOrdinateur;
import com.example.computer_horizon.models.Processeur;
import com.example.computer_horizon.models.Utilisateur;
import com.example.computer_horizon.services.PanierOrdinateurRepositoryService;
import com.example.computer_horizon.services.PanierRepositoryService;
import com.example.computer_horizon.services.UserRepositoryService;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


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
    SharedPreferences preferencesToken;
    Utilisateur utilisateur;
    List<Utilisateur> users;
    int index;
    private List<Panier> pan;
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

        pan = new ArrayList<>();
        index =0;
        preferencesToken = getSharedPreferences("token", MODE_PRIVATE);

        UserRepositoryService.query().enqueue(new Callback<List<Utilisateur>>() {
            @Override
            public void onResponse(Call<List<Utilisateur>> call, Response<List<Utilisateur>> response) {
                users = response.body();
                Log.i("test",response.body().toString());
                findUser();
            }


            @Override
            public void onFailure(Call<List<Utilisateur>> call, Throwable t) {

            }
        });


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

    public void findUser(){
        try {
            for(int i=0;i<users.size();i++){
                if(Decode.getUniqueName(preferencesToken.getString("token",null)).equals(users.get(i).getMail())){
                    utilisateur = users.get(i);
                }
            }
            PanierRepositoryService.query().enqueue(new Callback<List<Panier>>() {
                @Override
                public void onResponse(Call<List<Panier>> call, Response<List<Panier>> response) {
                    pan = response.body();
                    for(int i =0;i<pan.size();i++){
                        if(pan.get(i).getMail().equals(utilisateur.getMail())){
                            index = pan.get(i).getId();
                        }
                    }
                }
                @Override
                public void onFailure(Call<List<Panier>> call, Throwable t) {
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void ajouterPanierOrdi(View view){
        PanierOrdinateur ordi = new PanierOrdinateur(0,"");

        ordi.setId(index);
        ordi.setNom(nom.getText().toString());
        PanierOrdinateurRepositoryService.post(ordi).enqueue(new Callback<PanierOrdinateur>() {
            @Override
            public void onResponse(Call<PanierOrdinateur> call, Response<PanierOrdinateur> response) {
                Toast.makeText(OrdinateurAll.this, "Article ajouté", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<PanierOrdinateur> call, Throwable t) {

            }
        });
    }
}
