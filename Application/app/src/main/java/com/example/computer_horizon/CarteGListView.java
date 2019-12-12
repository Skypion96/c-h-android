package com.example.computer_horizon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.computer_horizon.models.CarteG;
import com.example.computer_horizon.models.CarteGAdapter;
import com.example.computer_horizon.models.Processeur;
import com.example.computer_horizon.models.ProcesseurAdapter;
import com.example.computer_horizon.services.CarteGraphiqueRepositoryService;
import com.example.computer_horizon.services.ProcesseurRepositoryService;

import java.io.Serializable;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CarteGListView extends AppCompatActivity implements AdapterView.OnItemClickListener, Serializable {

    private CarteGAdapter adapter;
    private ListView mListView;
    private List<CarteG> cartes;
    public static final String EXTRA_MAIN_ACTIVITY = "EXTRA_MAIN_ACTIVITY";



    private void populateListView(List<CarteG> carteG) {
        mListView = findViewById(R.id.lv_cg);
        adapter = new CarteGAdapter(this,R.id.lv_cg,carteG);
        mListView.setAdapter(adapter);
        cartes=carteG;
        mListView.setOnItemClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carte_glist_view);


        Call<List<CarteG>> call = CarteGraphiqueRepositoryService.query();
        call.enqueue(new Callback<List<CarteG>>() {

            @Override
            public void onResponse(Call<List<CarteG>> call, Response<List<CarteG>> response) {
                populateListView(response.body());

            }
            @Override
            public void onFailure(Call<List<CarteG>> call, Throwable throwable) {
                Toast.makeText(CarteGListView.this, throwable.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
        final CarteG bookmark = cartes.get(i);
        Intent intent = new Intent(CarteGListView.this, CarteGViewAll.class);
        intent.putExtra(EXTRA_MAIN_ACTIVITY,bookmark);
        Log.i("ordi",bookmark.getMarque());
        startActivity(intent);
    }
}