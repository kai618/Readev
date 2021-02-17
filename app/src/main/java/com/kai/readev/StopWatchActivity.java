package com.kai.readev;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class StopWatchActivity extends AppCompatActivity {

    private final StopWatch watch = new StopWatch();
    TextView tv;
    boolean wasRunning = false;

    private final String SECONDS = "S";
    private final String WAS_RUNNING = "W";

    private boolean shouldExit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("life", "onCreate");

        setContentView(R.layout.activity_stop_watch);
        tv = findViewById(R.id.stopwatch_view);

        if (savedInstanceState != null) {
            long sec = savedInstanceState.getLong(SECONDS);
            wasRunning = savedInstanceState.getBoolean(WAS_RUNNING);

            tv.setText(getTimeString(sec));
            watch.setSeconds(sec);
            if (wasRunning) watch.start(seconds -> tv.setText(getTimeString(seconds)));
        }
    }

    public void onStart(View view) {
        wasRunning = true;
        watch.start(sec -> tv.setText(getTimeString(sec)));
    }

    String getTimeString(long sec) {
        long h = sec / 3600;
        long m = (sec % 3600) / 60;
        long s = sec % 60;
        return String.format(Locale.getDefault(), "%d:%02d:%02d", h, m, s);
    }

    public void onStop(View view) {
        watch.stop();
        wasRunning = false;
    }

    public void onReset(View view) {
        wasRunning = false;
        watch.reset();
        tv.setText(R.string.watch_init);
    }

    public void onNavigate(View view) {
        startActivity(new Intent(this, BeerActivity.class));
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("life", "onRestart");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("life", "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("life", "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("life", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("life", "onStop");
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong(SECONDS, watch.getSeconds());
        outState.putBoolean(WAS_RUNNING, wasRunning);
        Log.i("life", "onSaveInstanceState 1");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("life", "onDestroy");
    }

    @Override
    public void onBackPressed() {
        if (shouldExit) super.onBackPressed();
        shouldExit = true;
        Toast.makeText(this, "Press again to exit", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(() -> shouldExit = false, 2000);
    }
}

