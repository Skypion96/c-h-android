package com.example.computer_horizon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.computer_horizon.models.CarteG;
import com.example.computer_horizon.models.Panier;
import com.example.computer_horizon.models.PanierCarteGraphique;
import com.example.computer_horizon.models.PanierOrdinateur;
import com.example.computer_horizon.models.Processeur;
import com.example.computer_horizon.models.Utilisateur;
import com.example.computer_horizon.services.PanierCarteGraphiqueRepositoryService;
import com.example.computer_horizon.services.PanierOrdinateurRepositoryService;
import com.example.computer_horizon.services.PanierRepositoryService;
import com.example.computer_horizon.services.UserRepositoryService;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CarteGViewAll extends AppCompatActivity {

    TextView nom;
    TextView marque;
    TextView prix;
    TextView frequence;
    TextView memoireVideo;
    SharedPreferences preferencesToken;
    Utilisateur utilisateur;
    List<Utilisateur> users;
    int index;
    private List<Panier> pan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carte_gview_all);
        CarteG cg = (CarteG) getIntent().getSerializableExtra(CarteGListView.EXTRA_MAIN_ACTIVITY);
        NumberFormat nm = NumberFormat.getNumberInstance();
        nom = findViewById(R.id.nom_cg);
        marque = findViewById(R.id.marque_cg);
        prix = findViewById(R.id.prix_cg);
        frequence = findViewById(R.id.frequencecg);
        memoireVideo = findViewById(R.id.memoireVideo_cg);

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

        nom.setText(cg.getNom());
        marque.setText(cg.getMarque());
        prix.setText(nm.format(cg.getPrix()));
        frequence.setText(cg.getFrequence());
        memoireVideo.setText(cg.getMemoireV());

    }

    public void ajouterPanierCG(View view){
        PanierCarteGraphique cg = new PanierCarteGraphique(0,"");

        cg.setId(index);
        cg.setNom(nom.getText().toString());
        PanierCarteGraphiqueRepositoryService.post(cg).enqueue(new Callback<PanierCarteGraphique>() {
            @Override
            public void onResponse(Call<PanierCarteGraphique> call, Response<PanierCarteGraphique> response) {
                Toast.makeText(CarteGViewAll.this, "Article ajouté", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<PanierCarteGraphique> call, Throwable t) {

            }
        });
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
}
