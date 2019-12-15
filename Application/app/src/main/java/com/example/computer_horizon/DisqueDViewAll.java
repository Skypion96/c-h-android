package com.example.computer_horizon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.computer_horizon.models.CarteG;
import com.example.computer_horizon.models.DisqueD;
import com.example.computer_horizon.models.Panier;
import com.example.computer_horizon.models.PanierDisqueDur;
import com.example.computer_horizon.models.PanierOrdinateur;
import com.example.computer_horizon.models.Utilisateur;
import com.example.computer_horizon.services.PanierDisqueDurRepositoryService;
import com.example.computer_horizon.services.PanierOrdinateurRepositoryService;
import com.example.computer_horizon.services.PanierRepositoryService;
import com.example.computer_horizon.services.UserRepositoryService;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DisqueDViewAll extends AppCompatActivity {

    TextView nom;
    TextView marque;
    TextView prix;
    TextView capacite;
    TextView ssd;
    TextView interne;
    SharedPreferences preferencesToken;
    Utilisateur utilisateur;
    List<Utilisateur> users;
    int index;
    private List<Panier> pan;
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

    public void ajouterPanierDD(View view){
        PanierDisqueDur dd = new PanierDisqueDur(0,"");

        dd.setId(index);
        dd.setNom(nom.getText().toString());
        PanierDisqueDurRepositoryService.post(dd).enqueue(new Callback<PanierDisqueDur>() {
            @Override
            public void onResponse(Call<PanierDisqueDur> call, Response<PanierDisqueDur> response) {
                Toast.makeText(DisqueDViewAll.this, "Article ajouté", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<PanierDisqueDur> call, Throwable t) {

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
