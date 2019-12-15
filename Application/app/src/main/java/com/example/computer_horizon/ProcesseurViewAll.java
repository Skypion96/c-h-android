package com.example.computer_horizon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.computer_horizon.models.Panier;
import com.example.computer_horizon.models.PanierOrdinateur;
import com.example.computer_horizon.models.PanierProcesseur;
import com.example.computer_horizon.models.Processeur;
import com.example.computer_horizon.models.Utilisateur;
import com.example.computer_horizon.services.PanierOrdinateurRepositoryService;
import com.example.computer_horizon.services.PanierProcesseurRepositoryService;
import com.example.computer_horizon.services.PanierRepositoryService;
import com.example.computer_horizon.services.UserRepositoryService;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProcesseurViewAll extends AppCompatActivity {

    TextView nom;
    TextView marque;
    TextView prix;
    TextView frequence;
    TextView nbCoeurs;
    SharedPreferences preferencesToken;
    Utilisateur utilisateur;
    List<Utilisateur> users;
    int index;
    private List<Panier> pan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_processeur_view_all);
        Processeur proc = (Processeur) getIntent().getSerializableExtra(ProcesseurListView.EXTRA_MAIN_ACTIVITY);
        NumberFormat nm = NumberFormat.getNumberInstance();
        nom = findViewById(R.id.nom_proc);
        marque = findViewById(R.id.marque_proc);
        prix = findViewById(R.id.prix_proc);
        frequence = findViewById(R.id.frequenceProc);
        nbCoeurs = findViewById(R.id.nbCoeurs_proc);

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


        nom.setText(proc.getNom());
        marque.setText(proc.getMarque());
        prix.setText(nm.format(proc.getPrix()));
        frequence.setText(proc.getFrequence());
        nbCoeurs.setText(nm.format(proc.getNbCoeurs()));

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
    public void ajouterPanierProc(View view){
        PanierProcesseur proc = new PanierProcesseur(0,"");

        proc.setId(index);
        proc.setNom(nom.getText().toString());
        PanierProcesseurRepositoryService.post(proc).enqueue(new Callback<PanierProcesseur>() {
            @Override
            public void onResponse(Call<PanierProcesseur> call, Response<PanierProcesseur> response) {

            }

            @Override
            public void onFailure(Call<PanierProcesseur> call, Throwable t) {

            }
        });
    }
}
