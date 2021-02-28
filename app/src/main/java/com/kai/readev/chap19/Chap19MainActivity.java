package com.kai.readev.chap19;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.widget.TextView;

import com.kai.readev.R;
import com.kai.readev.chap7.Chap7TopMenuActivity;

import java.util.Locale;

public class Chap19MainActivity extends AppCompatActivity {
    private OdometerService odometerService;
    private boolean bound = false;
    private final int PERMISSION_REQUEST_CODE = 678;
    private final int NOTIFICATION_ID = 1234;

    private final ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            OdometerService.OdometerBinder odometerBinder = (OdometerService.OdometerBinder) service;
            odometerService = odometerBinder.getOdometerService();
            bound = true;
            displayDistance();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            bound = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chap19_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (ContextCompat.checkSelfPermission(this, OdometerService.PERMISSION_STRING)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{OdometerService.PERMISSION_STRING},
                    PERMISSION_REQUEST_CODE);
        } else {
            bindLocationService();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (bound) {
            unbindService(connection);
            bound = false;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    bindLocationService();
                } else {
                    showLocationPermissionNotification();
                }
            }
        }

    }

    private void bindLocationService() {
        Intent intent = new Intent(this, OdometerService.class);
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    private void displayDistance() {
        TextView tv = findViewById(R.id.distance);
        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (bound && odometerService != null) {
                    double distance = odometerService.getDistance();
                    String str = String.format(Locale.getDefault(), "%.2f miles", distance);
                    tv.setText(str);
                    handler.postDelayed(this, 1000);
                }
            }
        });
    }

    private void showLocationPermissionNotification() {
        String channelId = null;
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            channelId = "LocPerNot";
            NotificationChannel channel = new NotificationChannel(
                    channelId,
                    "LocationPermissionNotification",
                    NotificationManager.IMPORTANCE_HIGH);
            manager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, channelId)
                .setSmallIcon(android.R.drawable.sym_def_app_icon)
                .setContentTitle("Odometer")
                .setContentText("Location permission required")
                .setAutoCancel(true);

        Intent intent = new Intent(this, Chap19MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(
                this,
                0,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);
        manager.notify(NOTIFICATION_ID, builder.build());
    }
}