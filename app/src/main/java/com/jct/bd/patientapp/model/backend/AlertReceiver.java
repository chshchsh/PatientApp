package com.jct.bd.patientapp.model.backend;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

import com.jct.bd.patientapp.controller.MainActivity;
import com.jct.bd.patientapp.controller.OpeningActivity;

public class AlertReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        createNotificationChannel(context);
    }
    private void createNotificationChannel(Context context) {
        Intent snoozeIntent = new Intent(context, OpeningActivity.class);
        PendingIntent snoozePendingIntent =
                PendingIntent.getBroadcast(context, 0, snoozeIntent, 0);

        NotificationCompat.Builder builder2 = new NotificationCompat.Builder(context, "yourId")
                .setSmallIcon(android.R.drawable.ic_btn_speak_now)// todo: need to be changed
                .setContentTitle("Hey there!")
                .setContentText("How are you doing?")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(snoozePendingIntent)
                .setAutoCancel(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "a";
            String description = "b";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("yourId", name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
            notificationManager.notify(1, builder2.build());
        }
    }
}
