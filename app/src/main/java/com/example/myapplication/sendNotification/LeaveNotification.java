package com.example.myapplication.sendNotification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.myapplication.R;

public class LeaveNotification extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationCompat.Builder notification = new NotificationCompat.Builder(context,"onTripDay")
                .setSmallIcon(R.drawable.logo)
                .setContentTitle("Have you backpacked your trip??")
                .setContentText("Let's go!!!");
        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(context);
        managerCompat.notify(201, notification.build());
    }
}
