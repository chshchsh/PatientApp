package com.jct.bd.patientapp.controller.fragments;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.jct.bd.patientapp.R;
import com.jct.bd.patientapp.model.backend.FactoryBackend;
import com.jct.bd.patientapp.model.datasource.FireBase_DB_manager;

import java.lang.reflect.Array;
import java.util.Dictionary;
import java.util.List;

public class Module3 extends Fragment implements View.OnClickListener {
    View view;
    MediaPlayer player;
    Button pause,play;
    Dictionary<String,Integer> audios;
    FireBase_DB_manager backend;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_module3,container,false);
        pause = view.findViewById(R.id.pause);
        play = view.findViewById(R.id.play);
        init();
        loadFragment(new Module1());
        return inflater.inflate(R.layout.fragment_module3, container, false);
    }
    public void init(){
        audios.put("rak",R.raw.rak);
        audios.put("rak",R.raw.rak);
        audios.put("rak",R.raw.rak);
        audios.put("rak",R.raw.rak);
        audios.put("rak",R.raw.rak);
        audios.put("rak",R.raw.rak);
        audios.put("rak",R.raw.rak);
        audios.put("rak",R.raw.rak);
        audios.put("rak",R.raw.rak);
        audios.put("rak",R.raw.rak);
    }
    public void play(View v){
        if(player == null){
            player = MediaPlayer.create(getContext(),audios.get("rak"));
            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    stopPlayer();
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
            play.setVisibility(View.GONE);
            pause.setVisibility(View.VISIBLE);
            play(v);
        }
        if (v == pause){
            pause.setVisibility(View.GONE);
            play.setVisibility(View.VISIBLE);
            pause(v);
        }
    }
    private void loadFragment(Fragment fragment) {
        // create a FragmentManager
        FragmentManager fm = getActivity().getSupportFragmentManager();
        // create a FragmentTransaction to begin the transaction and replace the Fragment
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        // replace the FrameLayout with new Fragment
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit(); // save the changes
    }
}
