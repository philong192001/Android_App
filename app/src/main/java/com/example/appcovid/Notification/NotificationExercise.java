package com.example.appcovid.Notification;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;

import com.google.android.material.snackbar.Snackbar;

public class NotificationExercise extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        notificationChanel();

    }

    private void notificationChanel(){
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(Utils.CHANNEL_ID, Utils.CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);

            channel.setDescription(Utils.CHANNEL_DESC);

            NotificationManager managerCompat = getSystemService(NotificationManager.class);
            managerCompat.createNotificationChannel(channel);
        }
    }
}
