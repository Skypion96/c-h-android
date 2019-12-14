package com.example.computer_horizon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.computer_horizon.services.OrdinateurRepositoryService;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MAIN_ACTIVITY = "EXTRA_MAIN_ACTIVITY";
    private com.example.computer_horizon.models.Ordinateur o =new com.example.computer_horizon.models.Ordinateur("tt","tt",3.25,"tt","tt","55",5,false,"ttt",5,"tt","pppp",5.00);

    Button panier ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        panier =  findViewById(R.id.panier);
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

    public void startDisqueDur(View view) {
        Intent intent = new Intent(MainActivity.this, DisqueDListView.class);
        startActivity(intent);
    }

    public void startInscription(View view) {
        Intent intent = new Intent(MainActivity.this, Inscription.class);
        startActivity(intent);
    }

    public void delayedNotification(Context context, long delay, int notificationId){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentTitle("Oublié de passer la commande ?")
                .setContentText("Il reste des articles dans votre Panier. Commander ?")
                .setAutoCancel(true)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));

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

    public void doQuit(View view){
        delayedNotification(getApplicationContext(), 5000, 0);
        finish();
    }

    public void isConnected(View view){
        SharedPreferences preferencesToken = getSharedPreferences("token", MODE_PRIVATE);
        String token = preferencesToken.getString("token", null);
        if(token != null){
            panier.setEnabled(true);
            panier.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    /*Intent intent = new Intent(MainActivity.this, Panier.class);
                    startActivity(intent);*/
                }
            });
        }
        else {
            panier.setEnabled(false);
            panier.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(MainActivity.this, "You're not connected", Toast.LENGTH_SHORT);
                }
            });
        }
    }

    public void doContact(View view){
        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto","la179682@student.helha.be", null));
        startActivity(Intent.createChooser(intent, "Envoyer via"));
    }
}
