package com.jct.bd.patientapp.controller;

import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.jct.bd.patientapp.R;
import com.jct.bd.patientapp.model.backend.FactoryBackend;
import com.jct.bd.patientapp.model.backend.IBackend;
import com.jct.bd.patientapp.model.entities.Patient;

import java.util.Dictionary;
import java.util.Hashtable;

public class Module31 extends AppCompatActivity implements View.OnClickListener{
    IBackend backend = FactoryBackend.getInstance();
    Patient patient;
    TextView relax,relax2;
    MediaPlayer player;
    Button pause,play;
    Dictionary<String,Integer> audios = new Hashtable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module31);
        relax = findViewById(R.id.Relax);
        relax2 = findViewById(R.id.Relax2);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/yourfont.ttf");
        relax.setTypeface(typeface);
        relax2.setTypeface(typeface);
        pause = findViewById(R.id.pause3);
        play = findViewById(R.id.play3);
        pause.setOnClickListener(this);
        play.setOnClickListener(this);
        init();
    }


    public void getInstance(String email)
    {
        this.patient = backend.getPatient(email);
    }



    public void init(){
        audios.put("breathing",R.raw.breathing);
        audios.put("jacobson",R.raw.jacobson);
    }
    public void play(View v){
        if(player == null){
            player = MediaPlayer.create(getApplicationContext(),audios.get(patient.getFileName()));
            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    finish();
                }
            });
        }
        player.start();
    }
    public void pause(View v){
        if(player != null){
            player.pause();
        }
    }
    private void stopPlayer(){
        if (player != null){
            player.release();
            player = null;
        }
    }

    @Override
    public void onClick(View v) {
        if (v == play){
            play.setVisibility(View.INVISIBLE);
            pause.setVisibility(View.VISIBLE);
            play(v);
        }
        if (v == pause){
            pause.setVisibility(View.INVISIBLE);
            play.setVisibility(View.VISIBLE);
            pause(v);
        }
    }
}
