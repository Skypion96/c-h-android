package com.example.computer_horizon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.auth0.android.jwt.JWT;
import com.example.computer_horizon.models.CarteG;
import com.example.computer_horizon.models.Utilisateur;
import com.example.computer_horizon.services.CarteGraphiqueRepositoryService;
import com.example.computer_horizon.services.UserRepositoryService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Modif_Profil extends AppCompatActivity {

    EditText eTNom;
    EditText eTPrenom;
    EditText eTTel;
    EditText eTRue;
    EditText eTNumero;
    EditText eTCP;
    EditText eTVille;
    EditText etMDP;
    EditText etMail;
    String value4 ;
    List<Utilisateur> users ;
    SharedPreferences preferencesToken;
    Utilisateur utilisateur;
    JWT jwt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modif__profil);
        preferencesToken = getSharedPreferences("token", MODE_PRIVATE);

        eTNom = findViewById(R.id.eTNom);
        eTPrenom = findViewById(R.id.eTPrenom);
        eTTel = findViewById(R.id.eTTel);
        eTRue = findViewById(R.id.eTRue);
        eTNumero = findViewById(R.id.eTNum);
        eTCP = findViewById(R.id.eTCP);
        eTVille = findViewById(R.id.eTVille);

        Call<List<Utilisateur>> call = UserRepositoryService.query();
        call.enqueue(new Callback<List<Utilisateur>>() {

            @Override
            public void onResponse(Call<List<Utilisateur>> call, Response<List<Utilisateur>> response) {
                users =(response.body());
                findUser();

            }
            @Override
            public void onFailure(Call<List<Utilisateur>> call, Throwable throwable) {
            }
        });




    }

    public void ajout(View view){
        String value = eTCP.getText().toString();
        String value1 = eTNom.getText().toString();
        String value2 = eTPrenom.getText().toString();
        String value3 = utilisateur.getMail();
        for(int i=0;i<users.size();i++){
            if(users.get(i).getMail().equals(utilisateur.getMail())){
                value4 = utilisateur.getMdp();
            }
        }
        String value5 = eTTel.getText().toString();
        String value6 = eTRue.getText().toString();
        String value7 = eTNumero.getText().toString();
        String value8 = eTVille.getText().toString();
        int value9 = Integer.parseInt(value);

        Utilisateur u = new Utilisateur(value1,value2,value3,value4,value5,value6,value7,value9,value8);
        UserRepositoryService.put(u).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.i("user","sucess");
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

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
        } catch (Exception e) {
            e.printStackTrace();
        }
        setElement();
    }

    public void setElement(){
        eTNom.setText(utilisateur.getNomUtilisateur());
        eTPrenom.setText(utilisateur.getPrenomUtilisateur());
        eTTel.setText(utilisateur.getTel());
        eTRue.setText(utilisateur.getRue());
        eTNumero.setText(utilisateur.getNumRue());
        eTCP.setText(Integer.toString(utilisateur.getCp()));
        eTVille.setText(utilisateur.getVille());
    }


}
