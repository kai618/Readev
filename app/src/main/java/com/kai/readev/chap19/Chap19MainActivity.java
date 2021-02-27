package com.kai.readev.chap19;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.widget.TextView;

import com.kai.readev.R;

import java.util.Locale;

public class Chap19MainActivity extends AppCompatActivity {
    private OdometerService odometerService;
    private boolean bound = false;

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
        Intent intent = new Intent(this, OdometerService.class);
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (bound) {
            unbindService(connection);
            bound = false;
        }
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
}