package com.example.computer_horizon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.computer_horizon.services.OrdinateurRepositoryService;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MAIN_ACTIVITY = "EXTRA_MAIN_ACTIVITY";
    private com.example.computer_horizon.models.Ordinateur o =new com.example.computer_horizon.models.Ordinateur("tt","tt",3.25,"tt","tt","55",5,false,"ttt",5,"tt","pppp",5.00);

    private List<com.example.computer_horizon.models.Ordinateur> ordis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnNotification = findViewById(R.id.btnNotificationDelayed);

        btnNotification.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                delayedNotification();
            }
        });

    }
    public void startConnexion(View view) {
        Intent intent = new Intent(MainActivity.this, Connexion.class);
        startActivity(intent);
    }

    public void startOrdinateur(View view) {
        Intent intent = new Intent(MainActivity.this, Ordinateur.class);
        startActivity(intent);
    }

    public void startProcesseur(View view) {
        Intent intent = new Intent(MainActivity.this, ProcesseurListView.class);
        startActivity(intent);
    }

    public void startCarteGraphique(View view) {
        Intent intent = new Intent(MainActivity.this, CarteGListView.class);
        startActivity(intent);
    }

    public void delayedNotification(){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentTitle("Titre notification")
                .setContentText("Texte de la notification");

        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);

        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
    }
}
