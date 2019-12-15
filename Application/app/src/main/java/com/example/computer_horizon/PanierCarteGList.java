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
import com.example.computer_horizon.models.Panier;
import com.example.computer_horizon.models.PanierCarteGraphique;
import com.example.computer_horizon.models.PanierProcesseur;
import com.example.computer_horizon.models.Processeur;
import com.example.computer_horizon.models.ProcesseurAdapter;
import com.example.computer_horizon.models.Utilisateur;
import com.example.computer_horizon.services.CarteGraphiqueRepositoryService;
import com.example.computer_horizon.services.PanierCarteGraphiqueRepositoryService;
import com.example.computer_horizon.services.PanierProcesseurRepositoryService;
import com.example.computer_horizon.services.PanierRepositoryService;
import com.example.computer_horizon.services.ProcesseurRepositoryService;
import com.example.computer_horizon.services.UserRepositoryService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PanierCarteGList extends AppCompatActivity implements AdapterView.OnItemClickListener,Serializable {

    private CarteGAdapter adapter;
    private ListView mListView;
    private List<PanierCarteGraphique> panier;
    private List<CarteG> cartes;
    private List<CarteG> cc;
    private List<Panier> pan;
    public static final String EXTRA_MAIN_ACTIVITY = "EXTRA_MAIN_ACTIVITY";
    SharedPreferences preferencesToken;
    Utilisateur utilisateur;
    List<Utilisateur> users;
    int index;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panier_carte_glist);
        cartes = new ArrayList<>();
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


        Call<List<PanierCarteGraphique>> call = PanierCarteGraphiqueRepositoryService.query();
        call.enqueue(new Callback<List<PanierCarteGraphique>>() {
            @Override
            public void onResponse(Call<List<PanierCarteGraphique>> call, Response<List<PanierCarteGraphique>> response) {
                panier = response.body();
            }
            @Override
            public void onFailure(Call<List<PanierCarteGraphique>> call, Throwable throwable) {
                Toast.makeText(PanierCarteGList.this, throwable.getMessage(), Toast.LENGTH_LONG).show();
            }
        });



        Call<List<CarteG>> call2 = CarteGraphiqueRepositoryService.query();
        call2.enqueue(new Callback<List<CarteG>>() {
            @Override
            public void onResponse(Call<List<CarteG>> call, Response<List<CarteG>> response) {
                cc=(response.body());
            }

            @Override
            public void onFailure(Call<List<CarteG>> call, Throwable t) {
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
                            Log.i("test",""+cc.size());
                            populateListView(cc);


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

    private void populateListView(List<CarteG> carteG) {
        mListView = findViewById(R.id.lv_carteG_pan);
        for(int i =0;i<carteG.size();i++){
            for(int j=0;j<panier.size();j++){
                Log.i("test",""+index);
                if(carteG.get(i).getNom().equals(panier.get(j).getNom() )&& panier.get(j).getId() == index){
                    cartes.add(carteG.get(i));
                }
            }
        }
        adapter = new CarteGAdapter(this,R.id.lv_carteG_pan,cartes);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        final CarteG bookmark = cartes.get(i);
        Log.i("test",cartes.get(i).toString());
        Intent intent = new Intent(PanierCarteGList.this, PanierCGAll.class);
        intent.putExtra(EXTRA_MAIN_ACTIVITY,bookmark);
        startActivity(intent);
    }

    public void calculId(){


    }


}
