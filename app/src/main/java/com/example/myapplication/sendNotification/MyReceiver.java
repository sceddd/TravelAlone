package com.example.myapplication.sendNotification;

import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.myapplication.R;

public class MyReceiver extends BroadcastReceiver {
    public MyReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context,"123")
                .setSmallIcon(R.drawable.logo)
                .setContentTitle("Do you have a nice trip??")
                .setContentText("Rating for the trip");

        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(context);
        managerCompat.notify(200, builder.build());
    }
}