package com.example.computer_horizon;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import com.example.computer_horizon.models.Ordinateur;


public class ComposantAll extends AppCompatActivity {

    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_composant_all);
         Ordinateur ordi = (Ordinateur)getIntent().getSerializableExtra(Composant.EXTRA_MAIN_ACTIVITY);

        editText = findViewById(R.id.nom);
        editText.setText(ordi.getNom());
    }
}
