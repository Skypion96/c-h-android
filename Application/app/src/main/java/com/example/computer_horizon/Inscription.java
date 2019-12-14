package com.example.computer_horizon;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.computer_horizon.models.Utilisateur;
import com.example.computer_horizon.services.UserRepositoryService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Inscription extends AppCompatActivity {

    EditText eTNom_ins;
    EditText eTPrenom_ins;
    EditText eTTel_ins;
    EditText eTRue_ins;
    EditText eTNumero_ins;
    EditText eTCP_ins;
    EditText eTVille_ins;
    EditText etMDP_ins;
    EditText etMail_ins;
    String value4 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modif__profil);

        eTNom_ins = findViewById(R.id.eTNom_ins);
        eTPrenom_ins = findViewById(R.id.eTPrenom_ins);
        eTTel_ins = findViewById(R.id.eTTel_ins);
        eTRue_ins = findViewById(R.id.eTRue_ins);
        eTNumero_ins = findViewById(R.id.eTNum_ins);
        eTCP_ins = findViewById(R.id.eTCP_ins);
        eTVille_ins = findViewById(R.id.eTVille_ins);
        etMDP_ins = findViewById(R.id.etMDP_ins);
        etMail_ins = findViewById(R.id.etMail_ins);

    }

    public void ajout(View view){
        String value = eTCP_ins.getText().toString();
        String value1 = eTNom_ins.getText().toString();
        String value2 = eTPrenom_ins.getText().toString();
        String value3 = etMail_ins.getText().toString();
        value4 = etMDP_ins.getText().toString();
        String value5 = eTTel_ins.getText().toString();
        String value6 = eTRue_ins.getText().toString();
        String value7 = eTNumero_ins.getText().toString();
        String value8 = eTVille_ins.getText().toString();
        int value9 = Integer.parseInt(value);

        Utilisateur u = new Utilisateur(value1,value2,value3,value4,value5,value6,value7,value9,value8);

        UserRepositoryService.post(u).enqueue(new Callback<Utilisateur>() {
            @Override
            public void onResponse(Call<Utilisateur> call, Response<Utilisateur> response) {

            }

            @Override
            public void onFailure(Call<Utilisateur> call, Throwable t) {

            }
        });
    }
}
