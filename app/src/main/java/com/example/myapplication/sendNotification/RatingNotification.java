package com.example.myapplication.sendNotification;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.myapplication.R;
import com.example.myapplication.database.ConnSQL;
import com.example.myapplication.locationUI.LocationDetails;

public class RatingNotification extends BroadcastReceiver {
    @SuppressLint("UnspecifiedImmutableFlag")
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent ratingActivity = new Intent(context, LocationDetails.class);
        ConnSQL c = new ConnSQL();
        int userID = intent.getIntExtra("userID", 0);
        int locationID= intent.getIntExtra("locationID", 0);
        c.updateSet("HistoryBook","status","userID = '"+userID+"', locationID = '"+locationID+"')");
        ratingActivity.putExtra("LocationID",locationID);
        PendingIntent pendingIntent = PendingIntent.getActivity(context,100,ratingActivity,PendingIntent.FLAG_CANCEL_CURRENT);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context,"123")
                .setSmallIcon(R.drawable.logo)
                .setContentTitle("Do you have a nice trip??")
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .setContentText("Rating for the trip");
        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(context);
        managerCompat.notify(200, builder.build());

    }
}