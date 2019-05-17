package com.jct.bd.patientapp.model.entities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.jct.bd.patientapp.model.entities.MakeNotifications;

public class myTurnedOn extends BroadcastReceiver {
    public static final String mypreference = "mypref";
    public static final String Name = "person_name";
    public static final String passwordS = "password";
    @Override
    public void onReceive(Context context, Intent intent) {
        SharedPreferences sharedpreferences;
        sharedpreferences = context.getSharedPreferences(mypreference, Context.MODE_PRIVATE);
        if (sharedpreferences.contains(Name) && sharedpreferences.contains(passwordS)) {
            MakeNotifications.makeNotification(context, 8, 0, 1);
            MakeNotifications.makeNotification(context, 12, 0, 2);
            MakeNotifications.makeNotification(context, 16, 0, 3);
            MakeNotifications.makeNotification(context, 20, 0, 4);
        }
    }
}
