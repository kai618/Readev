package com.kai.readev;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class StopWatchActivity extends AppCompatActivity {

    private final StopWatch watch = new StopWatch();
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop_watch);
        tv = findViewById(R.id.stopwatch_view);
    }

    public void onStart(View view) {
        watch.start((h, mm, ss) -> {
            String time = String.format(Locale.getDefault(), "%d:%02d:%02d", h, mm, ss);
            tv.setText(time);
        });
    }

    public void onStop(View view) {
        watch.stop();
    }

    public void onReset(View view) {
        watch.reset();
        tv.setText(R.string.watch_init);
    }
}

