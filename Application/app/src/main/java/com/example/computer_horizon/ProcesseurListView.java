package com.example.computer_horizon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.computer_horizon.models.OrdinateurAdapter;
import com.example.computer_horizon.models.Processeur;
import com.example.computer_horizon.models.ProcesseurAdapter;
import com.example.computer_horizon.services.OrdinateurRepositoryService;
import com.example.computer_horizon.services.ProcesseurRepositoryService;

import java.io.Serializable;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProcesseurListView extends AppCompatActivity implements AdapterView.OnItemClickListener, Serializable {

    private ProcesseurAdapter adapter;
    private ListView mListView;
    private List<Processeur> procs;
    public static final String EXTRA_MAIN_ACTIVITY = "EXTRA_MAIN_ACTIVITY";



    private void populateListView(List<Processeur> proc) {
        mListView = findViewById(R.id.lv_proc);
        adapter = new ProcesseurAdapter(this,R.id.lv_proc,proc);
        mListView.setAdapter(adapter);
        procs=proc;
        mListView.setOnItemClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_processeur_list_view);


        Call<List<Processeur>> call = ProcesseurRepositoryService.query();
        call.enqueue(new Callback<List<Processeur>>() {

            @Override
            public void onResponse(Call<List<Processeur>> call, Response<List<Processeur>> response) {
                populateListView(response.body());

            }
            @Override
            public void onFailure(Call<List<Processeur>> call, Throwable throwable) {
                Toast.makeText(ProcesseurListView.this, throwable.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
        final Processeur bookmark = procs.get(i);
        Intent intent = new Intent(ProcesseurListView.this, ProcesseurViewAll.class);
        intent.putExtra(EXTRA_MAIN_ACTIVITY,bookmark);
        startActivity(intent);
    }
}
