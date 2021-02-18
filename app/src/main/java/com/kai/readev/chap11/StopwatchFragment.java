package com.kai.readev.chap11;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.kai.readev.R;
import com.kai.readev.StopWatch;

import java.util.Locale;

public class StopwatchFragment extends Fragment {
    private final StopWatch watch = new StopWatch();
    private boolean wasRunning = false;
    private TextView tv;

    private final String SECONDS = "S";
    private final String WAS_RUNNING = "W";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_stopwatch, container, false);
        Button startBtn = rootView.findViewById(R.id.start_btn);
        startBtn.setOnClickListener(v -> onClickStart());
        Button stopBtn = rootView.findViewById(R.id.stop_btn);
        stopBtn.setOnClickListener(v -> onClickStop());
        Button resetBtn = rootView.findViewById(R.id.reset_btn);
        resetBtn.setOnClickListener(v -> onClickReset());

        tv = rootView.findViewById(R.id.stopwatch_view);
        if (savedInstanceState != null) {
            long sec = savedInstanceState.getLong(SECONDS);
            wasRunning = savedInstanceState.getBoolean(WAS_RUNNING);
            tv.setText(getTimeString(sec));
            watch.setSeconds(sec);
            if (wasRunning) watch.start(seconds -> tv.setText(getTimeString(seconds)));
        }
        return rootView;
    }

    private void onClickStart() {
        wasRunning = true;
        watch.start(sec -> tv.setText(getTimeString(sec)));
    }

    private void onClickStop() {
        watch.stop();
        wasRunning = false;
    }

    private void onClickReset() {
        wasRunning = false;
        watch.reset();
        tv.setText(R.string.watch_init);
    }

    String getTimeString(long sec) {
        long h = sec / 3600;
        long m = (sec % 3600) / 60;
        long s = sec % 60;
        return String.format(Locale.getDefault(), "%d:%02d:%02d", h, m, s);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong(SECONDS, watch.getSeconds());
        outState.putBoolean(WAS_RUNNING, wasRunning);
    }
}