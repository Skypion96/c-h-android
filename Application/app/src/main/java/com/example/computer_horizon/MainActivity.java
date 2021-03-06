package com.example.computer_horizon;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.AlarmManager;
import android.app.Application;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.computer_horizon.models.PanierCarteGraphique;
import com.example.computer_horizon.models.PanierDisqueDur;
import com.example.computer_horizon.models.PanierOrdinateur;
import com.example.computer_horizon.models.PanierProcesseur;
import com.example.computer_horizon.services.OrdinateurRepositoryService;
import com.example.computer_horizon.services.PanierCarteGraphiqueRepositoryService;
import com.example.computer_horizon.services.PanierDisqueDurRepositoryService;
import com.example.computer_horizon.services.PanierOrdinateurRepositoryService;
import com.example.computer_horizon.services.PanierProcesseurRepositoryService;

import java.util.ArrayList;
import java.util.List;

import io.jsonwebtoken.Jwts;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MAIN_ACTIVITY = "EXTRA_MAIN_ACTIVITY";

    Button panier ;
    Button btnProfil;
    Button btnConnexion;
    Button btnIncription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        panier =  findViewById(R.id.panier);
        btnProfil = findViewById(R.id.btnProfil);
        btnConnexion = findViewById(R.id.btnConnexion);
        btnIncription = findViewById(R.id.button8);
        isConnected();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        if(isConnected()){
            delayedNotification(getApplicationContext(), 100, 0);
        }
    }
    public void startConnexion(View view) {
        SharedPreferences preferencesToken = getSharedPreferences("token", MODE_PRIVATE);
        String token = preferencesToken.getString("token", null);
        if(token != null){
            preferencesToken.edit().clear().commit();
            isConnected();
        }
        else{
            Intent intent = new Intent(MainActivity.this, Connexion.class);
            startActivity(intent);
        }
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

    public void startDisqueDur(View view) {
        Intent intent = new Intent(MainActivity.this, DisqueDListView.class);
        startActivity(intent);
    }

    public void startInscription(View view) {
        Intent intent = new Intent(MainActivity.this, Inscription.class);
        startActivity(intent);
    }

    public void startPanier(View view){
        Intent intent = new Intent(MainActivity.this, PanierAffichage.class);
        startActivity(intent);
    }

    public void startProfil(View view){
        Intent intent = new Intent(MainActivity.this, Profil_page.class);
        startActivity(intent);
    }

    public void delayedNotification(Context context, long delay, int notificationId){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentTitle("Oubli?? de vous d??connecter ?")
                .setContentText("Pensez ?? revenir vous d??connecter !")
                .setAutoCancel(true)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setPriority(Notification.PRIORITY_MAX)
                .setChannelId("channel_1");

        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);

        Notification notification = builder.build();

        Intent notifIntent = new Intent(context, MyNotificationPublisher.class);
        notifIntent.putExtra(MyNotificationPublisher.NOTIFICATION_ID, notificationId);
        notifIntent.putExtra(MyNotificationPublisher.NOTIFICATION, notification);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, notificationId, notifIntent, PendingIntent.FLAG_CANCEL_CURRENT);

        long futureInMillis = SystemClock.elapsedRealtime() + delay;
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, futureInMillis, pendingIntent);
        Log.e("onReceive: ", "Test" );
    }

    public boolean isConnected(){
        SharedPreferences preferencesToken = getSharedPreferences("token", MODE_PRIVATE);
        String token = preferencesToken.getString("token", null);
        if(token != null){
            panier.setEnabled(true);
            panier.setVisibility(View.VISIBLE);
            btnProfil.setEnabled(true);
            btnProfil.setVisibility(View.VISIBLE);
            btnConnexion.setText("D??connexion");
            btnIncription.setEnabled(false);
            btnIncription.setVisibility(View.INVISIBLE);
            return true;
        }
        else {
            panier.setEnabled(false);
            panier.setVisibility(View.INVISIBLE);
            btnProfil.setEnabled(false);
            btnProfil.setVisibility(View.INVISIBLE);
            btnConnexion.setText("Connexion");
            btnIncription.setEnabled(true);
            btnIncription.setVisibility(View.VISIBLE);
            return false;
        }


    }

    public void startContact(View view){
        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto","la179682@student.helha.be", null));
        startActivity(Intent.createChooser(intent, "Envoyer via"));
    }

}
