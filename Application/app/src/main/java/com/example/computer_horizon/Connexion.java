package com.example.computer_horizon;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.computer_horizon.models.Authenticate;
import com.example.computer_horizon.models.UserConnected;
import com.example.computer_horizon.services.UserRepositoryService;

public class Connexion extends AppCompatActivity {

    EditText mailText;
    EditText mdpText;
    boolean success = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);

        Button connexion = findViewById(R.id.connexion);

        mailText = findViewById(R.id.mail);
        mdpText = findViewById(R.id.mdp);

        connexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

    }

    public void login(){
        String mail = mailText.getText().toString();
        String mdp = mdpText.getText().toString();
        UserRepositoryService.getInstance().authenticate(mail, mdp).enqueue(new Callback<UserConnected>() {
            @Override
            public void onResponse(Call<UserConnected> call, Response<UserConnected> response) {
                if(response.body() != null){
                    SharedPreferences preferencesToken = getSharedPreferences("token", MODE_PRIVATE);

                    String token = response.body().getToken();
                    Toast.makeText(Connexion.this, response.body().getToken(), Toast.LENGTH_SHORT).show();

                    preferencesToken.edit().putString("token", token).apply();

                    Intent navigate = new Intent(Connexion.this, MainActivity.class);
                    startActivity(navigate);
                }
            }

            @Override
            public void onFailure(Call<UserConnected> call, Throwable t) {
                Toast.makeText(Connexion.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                mailText.setText("");
                mdpText.setText("");
            }
        });
    }

}
