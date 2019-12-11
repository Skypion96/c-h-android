package com.example.computer_horizon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.computer_horizon.models.Ordinateur;
import com.example.computer_horizon.models.OrdinateurAdapter;
import com.example.computer_horizon.services.OrdinateurRepositoryService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class Composant extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private OrdinateurAdapter adapter;
    private ListView mListView;
    private List<Ordinateur> ordis;



    private void populateListView(List<Ordinateur> ordi) {
        mListView = findViewById(R.id.lv_ordis);
        adapter = new OrdinateurAdapter(this,R.id.lv_ordis,ordi);
        mListView.setAdapter(adapter);
        ordis=ordi;
        mListView.setOnItemClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_composant);


        Call<List<Ordinateur>> call = OrdinateurRepositoryService.query();
        call.enqueue(new Callback<List<Ordinateur>>() {

            @Override
            public void onResponse(Call<List<Ordinateur>> call, Response<List<Ordinateur>> response) {
                populateListView(response.body());

            }
            @Override
            public void onFailure(Call<List<Ordinateur>> call, Throwable throwable) {
                Toast.makeText(Composant.this, throwable.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
        final Ordinateur bookmark = ordis.get(i);
        Log.i("ordi",bookmark.getMarque());
    }
}
