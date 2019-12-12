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
import com.example.computer_horizon.models.DisqueD;
import com.example.computer_horizon.models.DisqueDAdapter;
import com.example.computer_horizon.services.CarteGraphiqueRepositoryService;
import com.example.computer_horizon.services.DisqueDRepositoryService;

import java.io.Serializable;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DisqueDListView extends AppCompatActivity implements AdapterView.OnItemClickListener, Serializable {

    private DisqueDAdapter adapter;
    private ListView mListView;
    private List<DisqueD> disques;
    public static final String EXTRA_MAIN_ACTIVITY = "EXTRA_MAIN_ACTIVITY";



    private void populateListView(List<DisqueD> disqueD) {
        mListView = findViewById(R.id.lv_dd);
        adapter = new DisqueDAdapter(this,R.id.lv_dd,disqueD);
        mListView.setAdapter(adapter);
        disques=disqueD;
        mListView.setOnItemClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disque_dlist_view);


        Call<List<DisqueD>> call = DisqueDRepositoryService.query();
        call.enqueue(new Callback<List<DisqueD>>() {

            @Override
            public void onResponse(Call<List<DisqueD>> call, Response<List<DisqueD>> response) {
                populateListView(response.body());

            }
            @Override
            public void onFailure(Call<List<DisqueD>> call, Throwable throwable) {
                Toast.makeText(DisqueDListView.this, throwable.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
        final DisqueD bookmark = disques.get(i);
        Intent intent = new Intent(DisqueDListView.this, DisqueDViewAll.class);
        intent.putExtra(EXTRA_MAIN_ACTIVITY,bookmark);
        startActivity(intent);
    }

}
