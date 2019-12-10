package com.example.computer_horizon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.computer_horizon.models.Ordinateur;
import com.example.computer_horizon.services.OrdinateurRepositoryService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        OrdinateurRepositoryService.query().enqueue(new Callback<List<Ordinateur>>() {
            @Override
            public void onResponse(Call<List<Ordinateur>> call, Response<List<Ordinateur>> response) {
                Log.i("Ordinateur",response.body().toString());
            }

            @Override
            public void onFailure(Call<List<Ordinateur>> call, Throwable t) {
                Log.i("OrdinateurError",t.toString());
            }
        });

    }

}
