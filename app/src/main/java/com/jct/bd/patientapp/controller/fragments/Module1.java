package com.jct.bd.patientapp.controller.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jct.bd.patientapp.R;
import com.jct.bd.patientapp.controller.LoginActivity;
import com.jct.bd.patientapp.model.entities.Patient;


public class Module1 extends Fragment {
    private View view;
    private Patient patient;
    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_module1,container,false);









        return view;
    }
  }
