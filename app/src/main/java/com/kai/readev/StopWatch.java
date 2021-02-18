package com.kai.readev;

import android.os.Handler;


public class StopWatch {

    public interface TickListener {
        void onTick(long sec);
    }

    private long sec = 0;
    private boolean running = false;

    public long getSeconds() {
        return sec;
    }

    public void setSeconds(long sec) {
        this.sec = sec;
    }

    public void start(TickListener listener) {
        if (running) return;
        running = true;
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!running) return;
                sec++;
                listener.onTick(sec);
                handler.postDelayed(this, 1000);
            }
        }, 1000);
    }

    public void stop() {
        running = false;
    }

    public void reset() {
        sec = 0;
        running = false;
    }
}

