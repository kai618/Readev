package com.kai.readev;

import android.os.Handler;

interface TickListener {
    void onTick(long h, long mm, long ss);
}

class StopWatch {
    private long sec = 0;
    private boolean running = false;

    public void start(TickListener listener) {
        running = true;
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!running) return;
                sec++;
                long h = sec / 3600;
                long mm = (sec % 3600) / 60;
                long ss = sec % 60;
                listener.onTick(h, mm, ss);
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

