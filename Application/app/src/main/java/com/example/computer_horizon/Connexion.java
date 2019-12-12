package com.example.computer_horizon;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.computer_horizon.models.Authenticate;
import com.example.computer_horizon.models.Login;
import com.example.computer_horizon.models.UserConnected;
import com.example.computer_horizon.models.Utilisateur;
import com.example.computer_horizon.repository.UserClient;
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


        connexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mailText = findViewById(R.id.mail);
                String mail = mailText.getText().toString();
                mdpText = findViewById(R.id.mdp);
                String mdp = mdpText.getText().toString();

                //login(mail, mdp);
                if(success == true){
                    Intent navigate = new Intent(Connexion.this, MainActivity.class);
                    startActivity(navigate);
                }
                else{
                    mailText.setText("");
                    mdpText.setText("");
                }
            }
        });

    }

   /* private UserConnected login(String mail, String mdp){
        return UserRepositoryService.authenticate(mail, mdp);
        Call<UserConnected> call = UserRepositoryService.authenticate(mail, mdp);
        call.enqueue();
    }*/


    /*Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl("http://10.0.2.2:5001/")
            .addConverterFactory(GsonConverterFactory.create());


    Retrofit retrofit = builder.build();
    UserClient userClient = retrofit.create(UserClient.class);

    private void login(String mail, String mdp){

        UserRepositoryService.getInstance().authenticate(mail, mdp)

        Login login = new Login(mail, mdp);
        Call<Utilisateur> call = userClient.login(login);

        call.enqueue(new Callback<Utilisateur>() {
            @Override
            public void onResponse(Call<Utilisateur> call, Response<Utilisateur> response) {
                if (response.isSuccessful()) {
                    //Toast.makeText(Connexion.this, response.body().getToken(), Toast.LENGTH_SHORT).show();
                    SharedPreferences preferencesToken =getSharedPreferences("token", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferencesToken.edit();
                    //String token = response.body().getToken();
                    //editor.putString("token", token);
                    //editor.apply();
                    success = true;
                    Toast.makeText(Connexion.this, success+"It works", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(Connexion.this, "login not correct", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Utilisateur> call, Throwable t) {
                Toast.makeText(Connexion.this, "an error happened", Toast.LENGTH_SHORT).show();
            }
        });
    }*/

}
