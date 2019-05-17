package com.jct.bd.patientapp.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


import com.jct.bd.patientapp.R;
import com.jct.bd.patientapp.model.backend.FactoryBackend;
import com.jct.bd.patientapp.model.datasource.NotifyDataChange;
import com.jct.bd.patientapp.model.entities.Patient;

import java.util.List;

import static android.os.SystemClock.sleep;

public class OpeningActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_opening);
        FactoryBackend.getInstance().notifyToPatientList(new NotifyDataChange<List<Patient>>() {
            @Override
            public void OnDataChanged(List<Patient> obj) {

            }

            @Override
            public void onFailure(Exception exception) {

            }
        });

        Thread myThread = new Thread()
        {
            @Override
            public void run()
            {
                try {
                    sleep(1500);
                    Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                    startActivity(intent);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        myThread.start();

    }
}
