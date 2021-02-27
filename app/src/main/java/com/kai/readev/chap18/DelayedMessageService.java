package com.kai.readev.chap18;

import android.app.IntentService;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.kai.readev.R;
import com.kai.readev.chap7.Chap7TopMenuActivity;


public class DelayedMessageService extends IntentService {
    public static final String EXTRA_MESSAGE = "message";
    public static final int NOTIFICATION_ID = 1234;

    public DelayedMessageService() {
        super("DelayMessageService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        synchronized (this) {
            try {
                wait(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        String text = intent.getStringExtra(EXTRA_MESSAGE);
        showText(text);
    }

    private void showText(String text) {
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        String channelId = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            channelId = "channel_id";
            NotificationChannel channel = new NotificationChannel(
                    channelId,
                    "kai",
                    NotificationManager.IMPORTANCE_HIGH);
            channel.enableVibration(true);
            manager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, channelId)
                .setSmallIcon(android.R.drawable.sym_def_app_icon)
                .setContentTitle(getString(R.string.question))
                .setContentText(text)
                .setAutoCancel(true);

        Intent intent = new Intent(this, Chap7TopMenuActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(
                this,
                0,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);

        manager.notify(NOTIFICATION_ID, builder.build());
        Log.d("service", text);
    }
}