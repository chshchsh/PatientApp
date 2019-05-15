package com.jct.bd.patientapp.controller;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.jct.bd.patientapp.R;

public class OpeningActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_opening);

        ImageView imageView = (ImageView)findViewById(R.id.animImage);
        imageView.setBackgroundResource(R.drawable.opening_animation);
        openingAnimation = (AnimationDrawable) imageView.getBackground();
        openingAnimation.start();

        Thread myThread = new Thread()
        {
            @Override
            public void run()
            {
                try {
                    sleep(4500);
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
