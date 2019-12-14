package com.example.computer_horizon;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.example.computer_horizon.models.CarteG;
import com.example.computer_horizon.models.CarteGAdapter;
import com.example.computer_horizon.models.DisqueD;
import com.example.computer_horizon.models.DisqueDAdapter;
import com.example.computer_horizon.models.PanierCarteGraphique;
import com.example.computer_horizon.models.PanierDisqueDur;
import com.example.computer_horizon.services.CarteGraphiqueRepositoryService;
import com.example.computer_horizon.services.DisqueDRepositoryService;
import com.example.computer_horizon.services.PanierCarteGraphiqueRepositoryService;
import com.example.computer_horizon.services.PanierDisqueDurRepositoryService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PanierDisqueDurList extends AppCompatActivity implements Serializable {

    private DisqueDAdapter adapter;
    private ListView mListView;
    private List<PanierDisqueDur> panier;
    private List<DisqueD> disques;
    public static final String EXTRA_MAIN_ACTIVITY = "EXTRA_MAIN_ACTIVITY";



    private void populateListView(List<DisqueD> disqueD) {
        mListView = findViewById(R.id.lv_disqueD_pan);
        for(int i =0;i<disqueD.size();i++){
            for(int j=0;j<panier.size();j++){
                if(disqueD.get(i).getNom().equals(panier.get(j).getNom())){
                    disques.add(disqueD.get(i));
                }
            }
        }
        adapter = new DisqueDAdapter(this,R.id.lv_disqueD_pan,disques);
        mListView.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panier_disque_dur_list);
        disques = new ArrayList<>();

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
                populateListView(response.body());
            }

            @Override
            public void onFailure(Call<List<DisqueD>> call, Throwable t) {
            }
        });

    }


    /*@Override
    public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
        final DisqueD bookmark = disques.get(i);
        Intent intent = new Intent(PanierProcList.this, DisqueDViewAll.class);
        intent.putExtra(EXTRA_MAIN_ACTIVITY,bookmark);
        startActivity(intent);
    }*/



}
