package com.jct.bd.patientapp.controller.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.jct.bd.patientapp.R;
import com.jct.bd.patientapp.controller.LoginActivity;

public class Module2 extends Fragment {
    View view;
    TextView messageCalm;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_module2,container,false);
        messageCalm = view.findViewById(R.id.messageCalm);
        Thread myThread = new Thread()
        {
            @Override
            public void run()
            {
                try {
                    sleep(16000);
                    Intent intent = new Intent(getContext(),LoginActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        return inflater.inflate(R.layout.fragment_module2, container, false);
    }
}
