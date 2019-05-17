package com.jct.bd.patientapp.controller;

import com.jct.bd.patientapp.controller.fragments.Module2;
import com.jct.bd.patientapp.model.backend.FactoryBackend;
import com.jct.bd.patientapp.model.backend.IBackend;
import com.jct.bd.patientapp.model.datasource.Action;
import com.jct.bd.patientapp.model.entities.MakeNotifications;
import com.jct.bd.patientapp.model.entities.Message;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.jct.bd.patientapp.R;
import com.jct.bd.patientapp.model.entities.Type;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Module1 extends AppCompatActivity implements View.OnClickListener {
    TextView tv,textView;
    FloatingActionButton send;
    Message message = new Message();
    SeekBar sb;
    private Fragment myFragment;
    String id,email;
    IBackend backend = FactoryBackend.getInstance();
    String type;
    Boolean pressed = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module1);
        tv = findViewById(R.id.count);
        textView = findViewById(R.id.textView1);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/yourfont.ttf");
        textView.setTypeface(typeface);
        Intent intent = getIntent();
        type = intent.getStringExtra("module");
        id = intent.getStringExtra("id");
        email = intent.getStringExtra("email");
        send = findViewById(R.id.send);
        send.setOnClickListener(this);
        sb = findViewById(R.id.seek_bar);
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                tv.setText(Integer.toString(seekBar.getProgress()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        if(pressed) {
            message.setAnswer2(sb.getProgress());
            backend.updateMessage(message, new Action<String>() {
                @Override
                public void onSuccess(String obj) {
                }

                @Override
                public void onFailure(Exception exception) {
                }

                @Override
                public void onProgress(String status, double percent) {
                }
            });
        }
        if(!pressed) {
            message.setAnswer1(sb.getProgress());
            if (type.equals("BEGINNER"))
                message.setModule(Type.BEGINNER);
            if (type.equals("ADVANCED"))
                message.setModule(Type.ADVANCED);
            if (type.equals("FINISHED"))
                message.setModule(Type.FINISHED);
            message.setUserId(id);
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm");
            Date date = calendar.getTime();
            message.setTimeStamp(simpleDateFormat.format(date));
            backend.addMessage(message, new Action<String>() {
                @Override
                public void onSuccess(String obj) {
                    Toast.makeText(getBaseContext(), getString(R.string.insert) + obj, Toast.LENGTH_LONG).show();
                }

                @Override
                public void onFailure(Exception exception) {
                    Toast.makeText(getBaseContext(), getString(R.string.error) + exception.getMessage(), Toast.LENGTH_LONG).show();
                }

                @Override
                public void onProgress(String status, double percent) {

                }
            });
        }
        if(type.equals("BEGINNER")) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                Toast.makeText(this,"Have a great day!",Toast.LENGTH_LONG).show();
                this.finishAffinity();
            }
        }
        else if(type.equals("ADVANCED") || message.getAnswer1() < 7) {
            myFragment = new Module2();
            //send.setVisibility(View.INVISIBLE);
            loadFragment(myFragment);
        }
        else if(type.equals("FINISHED")) {
            if(!pressed) {
                pressed = true;
                sb.setProgress(0);
                Intent intent = new Intent(this, Module31.class);
                startActivity(intent);
            }
            else
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                Toast.makeText(this,"Have a great day!",Toast.LENGTH_LONG).show();
                this.finishAffinity();
            }

        }
    }
    private void loadFragment(Fragment fragment) {
        // create a FragmentManager
        FragmentManager fm = getSupportFragmentManager();
        // create a FragmentTransaction to begin the transaction and replace the Fragment
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        // replace the FrameLayout with new Fragment
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit(); // save the changes
    }

}
