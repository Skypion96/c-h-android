package com.example.computer_horizon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.computer_horizon.models.DisqueD;
import com.example.computer_horizon.models.DisqueDAdapter;
import com.example.computer_horizon.models.Panier_processeur;
import com.example.computer_horizon.models.Processeur;
import com.example.computer_horizon.models.ProcesseurAdapter;
import com.example.computer_horizon.services.DisqueDRepositoryService;
import com.example.computer_horizon.services.PanierProcesseurRepositoryService;
import com.example.computer_horizon.services.ProcesseurRepositoryService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PanierProcList extends AppCompatActivity implements Serializable {

    private ProcesseurAdapter adapter;
    private ListView mListView;
    private List<Panier_processeur> panier;
    private List<Processeur> procs;
    public static final String EXTRA_MAIN_ACTIVITY = "EXTRA_MAIN_ACTIVITY";



    private void populateListView(List<Processeur> proc) {
        mListView = findViewById(R.id.lv_proc_pan);
        /*for(int i =0;i<proc.size();i++){
            if(proc.get(i).getNom()==panier.get(i).getNom()){
                procs.add(proc.get(i));
                Log.i("test",procs.get(i).getMarque());
            }
        }*/
        adapter = new ProcesseurAdapter(this,R.id.lv_proc_pan,proc);
        mListView.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panier_proc_list);
        procs = new ArrayList<>();

        Call<List<Panier_processeur>> call = PanierProcesseurRepositoryService.query();
        call.enqueue(new Callback<List<Panier_processeur>>() {

            @Override
            public void onResponse(Call<List<Panier_processeur>> call, Response<List<Panier_processeur>> response) {
                panier = response.body();
            }
            @Override
            public void onFailure(Call<List<Panier_processeur>> call, Throwable throwable) {
                Toast.makeText(PanierProcList.this, throwable.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        Call<List<Processeur>> call2 = ProcesseurRepositoryService.query();
        call2.enqueue(new Callback<List<Processeur>>() {
            @Override
            public void onResponse(Call<List<Processeur>> call, Response<List<Processeur>> response) {
                populateListView(response.body());
            }

            @Override
            public void onFailure(Call<List<Processeur>> call, Throwable t) {
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
