package com.example.computer_horizon;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.computer_horizon.models.Utilisateur;
import com.example.computer_horizon.services.UserRepositoryService;

public class Modif_Profil extends AppCompatActivity {

    EditText eTNom;
    EditText eTPrenom;
    EditText eTTel;
    EditText eTRue;
    EditText eTNumero;
    EditText eTCP;
    EditText eTVille;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modif__profil);

        eTNom = findViewById(R.id.eTNom);
        eTPrenom = findViewById(R.id.eTPrenom);
        eTTel = findViewById(R.id.eTTel);
        eTRue = findViewById(R.id.eTRue);
        eTNumero = findViewById(R.id.eTNum);
        eTCP = findViewById(R.id.eTCP);
        eTVille = findViewById(R.id.eTVille);




    }

    public void update(View view){
        String value = eTCP.getText().toString();
        Utilisateur u = new Utilisateur(eTNom.getText().toString(),eTPrenom.getText().toString(),
                "test@test.com","$2a$11$sxMn8WdjuQYRMy6cADqYLOiT6cshrmmbbnz5w1f7BzVDHl369jQ7i",
                eTTel.getText().toString(),eTRue.getText().toString(),eTNumero.getText().toString(),eTVille.getText().toString(),
                Integer.parseInt(value));

        UserRepositoryService.put(u);
    }
}
