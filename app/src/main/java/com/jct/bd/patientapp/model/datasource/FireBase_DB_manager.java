package com.jct.bd.patientapp.model.datasource;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.jct.bd.patientapp.model.backend.IBackend;
import com.jct.bd.patientapp.model.entities.Message;
import com.jct.bd.patientapp.model.entities.Patient;

import java.util.ArrayList;
import java.util.List;


public class FireBase_DB_manager implements IBackend {
    public List<Patient> patients = new ArrayList<>();
    private static ChildEventListener patientRefChildEventListener;
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
    public void notifyToPatientList(final NotifyDataChange<List<Patient>> notifyDataChange) {
        if (notifyDataChange != null) {
            if (patientRefChildEventListener != null) {
                notifyDataChange.onFailure(new Exception("first unNotify patient list"));
            }
            patients.clear();
            patientRefChildEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    Patient patient = dataSnapshot.getValue(Patient.class);
                    patients.add(patient);
                    notifyDataChange.OnDataChanged(patients);
                }
                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                }
                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {
                }
                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {
                    notifyDataChange.onFailure(databaseError.toException());
                }
            };
            PatientRef.addChildEventListener(patientRefChildEventListener);
        }
    }
    @Override
    public Patient getPatient(String email) {
        for (Patient patient:patients){
            if(patient.getEmail().equals(email))
                return patient;
        }
        return null;
    }

    @Override
    public Void updateMessage(final Message message, final Action<String> action) {
            final String key = (message.getKey());
            MessageRef.child(key).setValue(message).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    action.onFailure(e);
                    action.onProgress("error upload Driver data", 100);
                }
            });
            return null;
    }
}