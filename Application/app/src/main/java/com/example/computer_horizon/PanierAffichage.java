package com.example.computer_horizon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.computer_horizon.models.CarteG;
import com.example.computer_horizon.models.DisqueD;
import com.example.computer_horizon.models.Panier;
import com.example.computer_horizon.models.PanierCarteGraphique;
import com.example.computer_horizon.models.PanierDisqueDur;
import com.example.computer_horizon.models.PanierOrdinateur;
import com.example.computer_horizon.models.PanierProcesseur;
import com.example.computer_horizon.models.Processeur;
import com.example.computer_horizon.models.Utilisateur;
import com.example.computer_horizon.services.CarteGraphiqueRepositoryService;
import com.example.computer_horizon.services.DisqueDRepositoryService;
import com.example.computer_horizon.services.OrdinateurRepositoryService;
import com.example.computer_horizon.services.PanierCarteGraphiqueRepositoryService;
import com.example.computer_horizon.services.PanierDisqueDurRepositoryService;
import com.example.computer_horizon.services.PanierOrdinateurRepositoryService;
import com.example.computer_horizon.services.PanierProcesseurRepositoryService;
import com.example.computer_horizon.services.PanierRepositoryService;
import com.example.computer_horizon.services.ProcesseurRepositoryService;
import com.example.computer_horizon.services.UserRepositoryService;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PanierAffichage extends AppCompatActivity {

    private ProgressBar progressBar;
    private List<PanierCarteGraphique> panierCg;
    private List<CarteG> cartes;
    private List<PanierDisqueDur> panierDD;
    private List<DisqueD> disques;
    private List<PanierOrdinateur> panierOrdi;
    private List<PanierProcesseur> panierProc;
    private List<Processeur> procs;
    private List<com.example.computer_horizon.models.Ordinateur> ordis;
    private double total;
    private TextView totalPrix;
    private Button totalCalc;
    NumberFormat nm;
    private TextView panierOK;
    SharedPreferences preferencesToken;
    Utilisateur utilisateur;
    List<Utilisateur> users;
    int index;
    private List<Panier> pan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panier_affichage);

        progressBar = findViewById(R.id.progressBarA);
        progressBar.setVisibility(View.INVISIBLE);
        panierOK = findViewById(R.id.panierOK);
        loadListCG();
        loadListDD();
        loadListOrdi();
        loadListProc();
        totalPrix = findViewById(R.id.totalPrix);

        totalCalc = findViewById(R.id.totalCalc);
        totalCalc.setVisibility(View.VISIBLE);
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

    }

    private void launchBigTask() {
        progressBar.setMax(29);

        new AsyncFillProgressBar(new AsyncFillProgressBar.CallBack() {
            @Override
            public void onUpdate(int currentValue) {
                progressBar.setProgress(currentValue);
            }

            @Override
            public void onFinished() {
                nm = NumberFormat.getNumberInstance();
                suppressionArticlesPanier();
                panierOK.setText("Paiement effectu?? ! Merci de votre achat.");
                totalPrix.setText(nm.format(0.00));
            }
        }).execute(30);
    }

    public void startPaiement(View view){

        launchBigTask();
        progressBar.setVisibility(View.VISIBLE);
        panierOK.setText("Paiement en cours ...");

    }

    public void startPanierProc(View view){
        Intent intent = new Intent(PanierAffichage.this, PanierProcList.class);
        startActivity(intent);
    }

    public void startPanierOrdi(View view){
        Intent intent = new Intent(PanierAffichage.this, PanierOrdiList.class);
        startActivity(intent);
    }

    public void startPanierCarteG(View view){
        Intent intent = new Intent(PanierAffichage.this, PanierCarteGList.class);
        startActivity(intent);
    }

    public void startPanierDisqueD(View view){
        Intent intent = new Intent(PanierAffichage.this, PanierDisqueDurList.class);
        startActivity(intent);
    }

    public void loadListCG(){

        Call<List<PanierCarteGraphique>> call = PanierCarteGraphiqueRepositoryService.query();
        call.enqueue(new Callback<List<PanierCarteGraphique>>() {

            @Override
            public void onResponse(Call<List<PanierCarteGraphique>> call, Response<List<PanierCarteGraphique>> response) {
                panierCg = response.body();
            }
            @Override
            public void onFailure(Call<List<PanierCarteGraphique>> call, Throwable throwable) {
            }
        });

        Call<List<CarteG>> call2 = CarteGraphiqueRepositoryService.query();
        call2.enqueue(new Callback<List<CarteG>>() {
            @Override
            public void onResponse(Call<List<CarteG>> call, Response<List<CarteG>> response) {
                cartes =(response.body());
            }
            @Override
            public void onFailure(Call<List<CarteG>> call, Throwable t) {
            }
        });

    }

    public void loadListDD(){
        Call<List<PanierDisqueDur>> call = PanierDisqueDurRepositoryService.query();
        call.enqueue(new Callback<List<PanierDisqueDur>>() {
            @Override
            public void onResponse(Call<List<PanierDisqueDur>> call, Response<List<PanierDisqueDur>> response) {
                panierDD = response.body();
            }
            @Override
            public void onFailure(Call<List<PanierDisqueDur>> call, Throwable throwable) {
            }
        });

        Call<List<DisqueD>> call2 = DisqueDRepositoryService.query();
        call2.enqueue(new Callback<List<DisqueD>>() {
            @Override
            public void onResponse(Call<List<DisqueD>> call, Response<List<DisqueD>> response) {
                disques =(response.body());
            }
            @Override
            public void onFailure(Call<List<DisqueD>> call, Throwable t) {
            }
        });
    }

    public void loadListOrdi(){
        Call<List<PanierOrdinateur>> call = PanierOrdinateurRepositoryService.query();
        call.enqueue(new Callback<List<PanierOrdinateur>>() {
            @Override
            public void onResponse(Call<List<PanierOrdinateur>> call, Response<List<PanierOrdinateur>> response) {
                panierOrdi = response.body();
            }
            @Override
            public void onFailure(Call<List<PanierOrdinateur>> call, Throwable throwable) {
            }
        });

        Call<List<com.example.computer_horizon.models.Ordinateur>> call2 = OrdinateurRepositoryService.query();
        call2.enqueue(new Callback<List<com.example.computer_horizon.models.Ordinateur>>() {
            @Override
            public void onResponse(Call<List<com.example.computer_horizon.models.Ordinateur>> call, Response<List<com.example.computer_horizon.models.Ordinateur>> response) {
                ordis =(response.body());
            }
            @Override
            public void onFailure(Call<List<com.example.computer_horizon.models.Ordinateur>> call, Throwable t) {
            }
        });
    }

    public void loadListProc(){
        Call<List<PanierProcesseur>> call = PanierProcesseurRepositoryService.query();
        call.enqueue(new Callback<List<PanierProcesseur>>() {
            @Override
            public void onResponse(Call<List<PanierProcesseur>> call, Response<List<PanierProcesseur>> response) {
                panierProc = response.body();
            }
            @Override
            public void onFailure(Call<List<PanierProcesseur>> call, Throwable throwable) {
            }
        });

        Call<List<Processeur>> call2 = ProcesseurRepositoryService.query();
        call2.enqueue(new Callback<List<Processeur>>() {
            @Override
            public void onResponse(Call<List<Processeur>> call, Response<List<Processeur>> response) {
                procs =(response.body());
            }
            @Override
            public void onFailure(Call<List<Processeur>> call, Throwable t) {
            }
        });

    }

    public void suppressionArticlesPanier(){

        for(int i =0;i<procs.size();i++){
            for(int j = 0;j<panierProc.size();j++){
                if(procs.get(i).getNom().equals(panierProc.get(j).getNom()) && panierProc.get(j).getId() == index){
                    PanierProcesseurRepositoryService.delete(panierProc.get(j).getNom()).enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                        }
                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                        }
                    });
                }
            }
        }

        for(int i =0;i<disques.size();i++){
            for(int j = 0;j<panierDD.size();j++){
                if(disques.get(i).getNom().equals(panierDD.get(j).getNom())&& panierDD.get(j).getId() == index){
                    PanierDisqueDurRepositoryService.delete(panierDD.get(j).getNom()).enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                        }
                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                        }
                    });
                }
            }
        }
        for(int i =0;i<cartes.size();i++){
            for(int j = 0;j<panierCg.size();j++){
                if(cartes.get(i).getNom().equals(panierCg.get(j).getNom())&& panierCg.get(j).getId() == index){
                    PanierCarteGraphiqueRepositoryService.delete(panierCg.get(j).getNom()).enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                        }
                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                        }
                    });
                }
            }
        }

        for(int i =0;i<ordis.size();i++){
            for(int j = 0;j<panierOrdi.size();j++){
                if(ordis.get(i).getNom().equals(panierOrdi.get(j).getNom())&& panierOrdi.get(j).getId() == index){
                    PanierOrdinateurRepositoryService.delete(panierOrdi.get(j).getNom()).enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                        }
                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                        }
                    });
                }
            }
        }
    }

    public void retourAccueil(View view){
        Intent intent = new Intent(PanierAffichage.this, MainActivity.class);
        startActivity(intent);
    }

    public void total(View view){
        for(int i =0;i<procs.size();i++){
            for(int j = 0;j<panierProc.size();j++){
                if(procs.get(i).getNom().equals(panierProc.get(j).getNom())&& panierProc.get(j).getId() == index){
                    total += procs.get(i).getPrix();
                }
            }
        }

        for(int i =0;i<disques.size();i++){
            for(int j = 0;j<panierDD.size();j++){
                if(disques.get(i).getNom().equals(panierDD.get(j).getNom())&& panierDD.get(j).getId() == index){
                    total += disques.get(i).getPrix();
                }
            }
        }
        for(int i =0;i<cartes.size();i++){
            for(int j = 0;j<panierCg.size();j++){
                if(cartes.get(i).getNom().equals(panierCg.get(j).getNom())&& panierCg.get(j).getId() == index){
                    total += cartes.get(i).getPrix();
                }
            }
        }

        for(int i =0;i<ordis.size();i++){
            for(int j = 0;j<panierOrdi.size();j++){
                if(ordis.get(i).getNom().equals(panierOrdi.get(j).getNom())&& panierOrdi.get(j).getId() == index){
                    total += ordis.get(i).getPrix();
                }
            }
        }
        nm  = NumberFormat.getNumberInstance();

        totalPrix.setText(nm.format(total));
        totalCalc.setVisibility(View.INVISIBLE);
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
