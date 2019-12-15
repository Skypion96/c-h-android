package com.example.computer_horizon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.computer_horizon.models.CarteG;
import com.example.computer_horizon.models.CarteGAdapter;
import com.example.computer_horizon.models.PanierCarteGraphique;
import com.example.computer_horizon.models.PanierProcesseur;
import com.example.computer_horizon.models.Processeur;
import com.example.computer_horizon.models.ProcesseurAdapter;
import com.example.computer_horizon.services.CarteGraphiqueRepositoryService;
import com.example.computer_horizon.services.PanierCarteGraphiqueRepositoryService;
import com.example.computer_horizon.services.PanierProcesseurRepositoryService;
import com.example.computer_horizon.services.ProcesseurRepositoryService;

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
    public static final String EXTRA_MAIN_ACTIVITY = "EXTRA_MAIN_ACTIVITY";



    private void populateListView(List<CarteG> carteG) {
        mListView = findViewById(R.id.lv_carteG_pan);
        for(int i =0;i<carteG.size();i++){
            for(int j=0;j<panier.size();j++){
                if(carteG.get(i).getNom().equals(panier.get(j).getNom())){
                    cartes.add(carteG.get(i));
                }
            }
        }
        adapter = new CarteGAdapter(this,R.id.lv_carteG_pan,cartes);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panier_carte_glist);
        cartes = new ArrayList<>();

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
                populateListView(response.body());
            }

            @Override
            public void onFailure(Call<List<CarteG>> call, Throwable t) {
            }
        });

    }





    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        final CarteG bookmark = cartes.get(i);
        Log.i("test",cartes.get(i).toString());
        Intent intent = new Intent(PanierCarteGList.this, PanierCGAll.class);
        intent.putExtra(EXTRA_MAIN_ACTIVITY,bookmark);
        startActivity(intent);
    }
}
