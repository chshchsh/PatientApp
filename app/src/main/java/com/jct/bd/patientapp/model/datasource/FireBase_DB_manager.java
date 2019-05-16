package com.jct.bd.patientapp.model.datasource;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jct.bd.patientapp.model.backend.IBackend;
import com.jct.bd.patientapp.model.entities.Message;
import com.jct.bd.patientapp.model.entities.Patient;


public class FireBase_DB_manager implements IBackend {
    static FirebaseDatabase database = FirebaseDatabase.getInstance();
    static DatabaseReference PatientRef = database.getReference("patient");
    static DatabaseReference MessageRef = database.getReference("message");

    @Override
    public Void addPatient(final Patient patient, final Action<String> action) {
        String key = PatientRef.push().getKey();
        patient.setKey(key);
        PatientRef.child(key).setValue(patient).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                action.onSuccess(patient.getId());
                action.onProgress("upload Patient data", 100);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                action.onFailure(e);
                action.onProgress("error upload Patient data", 100);
            }
        });
        return null;
    }
    @Override
    public Void addMessage(final Message message, final Action<String> action) {
        String key = MessageRef.push().getKey();
        message.setKey(key);
        MessageRef.child(key).setValue(message).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                action.onSuccess(message.getUserId());
                action.onProgress("upload Message data", 100);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                action.onFailure(e);
                action.onProgress("error upload Message data", 100);
            }
        });
        return null;
    }
}