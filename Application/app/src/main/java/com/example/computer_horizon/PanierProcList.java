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

import com.example.computer_horizon.models.DisqueD;
import com.example.computer_horizon.models.Panier;
import com.example.computer_horizon.models.PanierProcesseur;
import com.example.computer_horizon.models.Processeur;
import com.example.computer_horizon.models.ProcesseurAdapter;
import com.example.computer_horizon.models.Utilisateur;
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

public class PanierProcList extends AppCompatActivity implements AdapterView.OnItemClickListener,Serializable {

    private ProcesseurAdapter adapter;
    private ListView mListView;
    private List<PanierProcesseur> panier;
    private List<Processeur> procs;
    public static final String EXTRA_MAIN_ACTIVITY = "EXTRA_MAIN_ACTIVITY";
    SharedPreferences preferencesToken;
    Utilisateur utilisateur;
    List<Utilisateur> users;
    int index;
    private List<Processeur> proc;
    private List<Panier> pan;

    private void populateListView(List<Processeur> proc) {
        mListView = findViewById(R.id.lv_proc_pan);
        for(int i =0;i<proc.size();i++){
            for(int j=0;j<panier.size();j++){
                if(proc.get(i).getNom().equals(panier.get(j).getNom())&& panier.get(j).getId() == index){
                    procs.add(proc.get(i));
                }
            }
        }
        adapter = new ProcesseurAdapter(this,R.id.lv_proc_pan,procs);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panier_proc_list);
        procs = new ArrayList<>();
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

        Call<List<PanierProcesseur>> call = PanierProcesseurRepositoryService.query();
        call.enqueue(new Callback<List<PanierProcesseur>>() {

            @Override
            public void onResponse(Call<List<PanierProcesseur>> call, Response<List<PanierProcesseur>> response) {
                panier = response.body();
            }
            @Override
            public void onFailure(Call<List<PanierProcesseur>> call, Throwable throwable) {
                Toast.makeText(PanierProcList.this, throwable.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        Call<List<Processeur>> call2 = ProcesseurRepositoryService.query();
        call2.enqueue(new Callback<List<Processeur>>() {
            @Override
            public void onResponse(Call<List<Processeur>> call, Response<List<Processeur>> response) {
                proc =(response.body());
            }

            @Override
            public void onFailure(Call<List<Processeur>> call, Throwable t) {
            }
        });

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        final Processeur bookmark = procs.get(i);
        Intent intent = new Intent(PanierProcList.this, PanierProcAll.class);
        intent.putExtra(EXTRA_MAIN_ACTIVITY,bookmark);
        startActivity(intent);
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
                            Log.i("test",""+proc.size());
                            populateListView(proc);


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
