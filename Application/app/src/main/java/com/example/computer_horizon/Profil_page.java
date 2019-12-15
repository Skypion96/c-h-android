package com.example.computer_horizon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.auth0.android.jwt.Claim;
import com.auth0.android.jwt.JWT;
import com.example.computer_horizon.models.Utilisateur;
import com.example.computer_horizon.services.UserRepositoryService;

import java.text.NumberFormat;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Profil_page extends AppCompatActivity {

    List<Utilisateur> users;
    String mailUser;
    SharedPreferences preferencesToken;
    Utilisateur utilisateur;

    TextView nomProfil;
    TextView prenomProfil;
    TextView mailProfil;
    TextView telProfil;
    TextView rueProfil;
    TextView villeProfil;
    TextView numeroProfil;
    TextView cpProfil;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil_page);
        preferencesToken = getSharedPreferences("token", MODE_PRIVATE);
        NumberFormat nm = NumberFormat.getNumberInstance();
        utilisateur = new Utilisateur("","","","","","","",0,"");
        nomProfil = findViewById(R.id.nom_Profil);
        prenomProfil = findViewById(R.id.prenom_Profil);
        mailProfil = findViewById(R.id.mail_Profil);
        telProfil = findViewById(R.id.tel_profile);
        villeProfil = findViewById(R.id.ville_profile);
        rueProfil = findViewById(R.id.rue_profile);
        numeroProfil = findViewById(R.id.numero_profile);
        cpProfil = findViewById(R.id.cp_profile);





        UserRepositoryService.query().enqueue(new Callback<List<Utilisateur>>() {
            @Override
            public void onResponse(Call<List<Utilisateur>> call, Response<List<Utilisateur>> response) {
                users = response.body();
                findUser();
            }

            @Override
            public void onFailure(Call<List<Utilisateur>> call, Throwable t) {

            }
        });



    }

    public void affichageProfil(){


        nomProfil.setText(utilisateur.getNomUtilisateur());
        prenomProfil.setText(utilisateur.getPrenomUtilisateur());
        mailProfil.setText(utilisateur.getMail());
        telProfil.setText(utilisateur.getTel());
        villeProfil.setText(utilisateur.getVille());
        rueProfil.setText(utilisateur.getRue());
        numeroProfil.setText(utilisateur.getNumRue());
        cpProfil.setText(""+utilisateur.getCp());
    }

    public void startModif(View view){
        Intent intent = new Intent(Profil_page.this, Modif_Profil.class);
        startActivity(intent);
    }

    public void startDelete(View view){
        findUser();
        Intent intent = new Intent(Profil_page.this, MainActivity.class);
        startActivity(intent);
        UserRepositoryService.delete(utilisateur.getMail()).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
        preferencesToken.edit().clear().commit();

    }

    public void retourAccueil(View view){
        Intent intent = new Intent(Profil_page.this, MainActivity.class);
        startActivity(intent);
    }

    public void findUser(){
        try {
            for(int i=0;i<users.size();i++){
                if(Decode.getUniqueName(preferencesToken.getString("token",null)).equals(users.get(i).getMail())){
                    utilisateur = users.get(i);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        affichageProfil();

    }
}
