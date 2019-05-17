package com.jct.bd.patientapp.controller.fragments;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

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
        Typeface typeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/yourfont.ttf");
        messageCalm.setTypeface(typeface);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getContext(),"Have a great day!",Toast.LENGTH_LONG).show();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    getActivity().finishAffinity();
                }
            }
        },16000);
        return inflater.inflate(R.layout.fragment_module2, container, false);
    }
}
