package com.jct.bd.patientapp.model.backend;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import com.jct.bd.patientapp.R;
import com.jct.bd.patientapp.controller.MainActivity;
import com.jct.bd.patientapp.controller.OpeningActivity;
import com.jct.bd.patientapp.model.entities.NotificationHelper;

public class AlertReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        NotificationHelper notificationHelper = new NotificationHelper(context);
        NotificationCompat.Builder nb = notificationHelper.getChannelNotification();
        notificationHelper.getManager().notify(1, nb.build());
    }
    private void createNotificationChannel(Context context) {

        Intent snoozeIntent = new Intent(context, OpeningActivity.class);
        PendingIntent snoozePendingIntent =
                PendingIntent.getBroadcast(context, 0, snoozeIntent, 0);

        NotificationCompat.Builder builder2 = new NotificationCompat.Builder(context, "yourId")
                .setSmallIcon(R.drawable.icon)// todo: need to be changed
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

    private void createNotification2(Context context) {
            NotificationManager notificationManager;
            Notification myNotification;
            Intent myIntent = new Intent(context, OpeningActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, myIntent, 0);

            myNotification = new NotificationCompat.Builder(context)
                    .setContentTitle("Exercise of Notification!")
                    .setContentText("Do Something...")
                    .setContentIntent(pendingIntent)
                    .setDefaults(Notification.DEFAULT_SOUND)
                    .setAutoCancel(true)
                    .setSmallIcon(R.drawable.icon)
                    .build();

            notificationManager =
                    (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(1, myNotification);
        }


}
