package com.example.computer_horizon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.computer_horizon.models.CarteG;
import com.example.computer_horizon.models.CarteGAdapter;
import com.example.computer_horizon.models.DisqueD;
import com.example.computer_horizon.models.DisqueDAdapter;
import com.example.computer_horizon.models.Panier;
import com.example.computer_horizon.models.PanierCarteGraphique;
import com.example.computer_horizon.models.PanierDisqueDur;
import com.example.computer_horizon.models.Utilisateur;
import com.example.computer_horizon.services.CarteGraphiqueRepositoryService;
import com.example.computer_horizon.services.DisqueDRepositoryService;
import com.example.computer_horizon.services.PanierCarteGraphiqueRepositoryService;
import com.example.computer_horizon.services.PanierDisqueDurRepositoryService;
import com.example.computer_horizon.services.PanierRepositoryService;
import com.example.computer_horizon.services.UserRepositoryService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PanierDisqueDurList extends AppCompatActivity implements AdapterView.OnItemClickListener,Serializable {

    private DisqueDAdapter adapter;
    private ListView mListView;
    private List<PanierDisqueDur> panier;
    private List<DisqueD> disques;
    private List<DisqueD> dd;
    private List<Panier> pan;
    public static final String EXTRA_MAIN_ACTIVITY = "EXTRA_MAIN_ACTIVITY";
    SharedPreferences preferencesToken;

    Utilisateur utilisateur;
    List<Utilisateur> users;
    int index;


    private void populateListView(List<DisqueD> disqueD) {
        mListView = findViewById(R.id.lv_disqueD_pan);
        for(int i =0;i<disqueD.size();i++){
            for(int j=0;j<panier.size();j++){
                if(disqueD.get(i).getNom().equals(panier.get(j).getNom())&& panier.get(j).getId() == index){
                    disques.add(disqueD.get(i));
                }
            }
        }
        adapter = new DisqueDAdapter(this,R.id.lv_disqueD_pan,disques);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(this);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panier_disque_dur_list);
        disques = new ArrayList<>();
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


        Call<List<PanierDisqueDur>> call = PanierDisqueDurRepositoryService.query();
        call.enqueue(new Callback<List<PanierDisqueDur>>() {

            @Override
            public void onResponse(Call<List<PanierDisqueDur>> call, Response<List<PanierDisqueDur>> response) {
                panier = response.body();
            }
            @Override
            public void onFailure(Call<List<PanierDisqueDur>> call, Throwable throwable) {
                Toast.makeText(PanierDisqueDurList.this, throwable.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        Call<List<DisqueD>> call2 = DisqueDRepositoryService.query();
        call2.enqueue(new Callback<List<DisqueD>>() {
            @Override
            public void onResponse(Call<List<DisqueD>> call, Response<List<DisqueD>> response) {
                dd=(response.body());
            }

            @Override
            public void onFailure(Call<List<DisqueD>> call, Throwable t) {
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
                            Log.i("test",""+dd.size());
                            populateListView(dd);


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

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        final DisqueD bookmark = disques.get(i);
        Intent intent = new Intent(PanierDisqueDurList.this, PanierDDAll.class);
        intent.putExtra(EXTRA_MAIN_ACTIVITY,bookmark);
        startActivity(intent);
    }





}
