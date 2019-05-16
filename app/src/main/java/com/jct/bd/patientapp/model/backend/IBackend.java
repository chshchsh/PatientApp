package com.jct.bd.patientapp.model.backend;


import com.jct.bd.patientapp.model.datasource.Action;
import com.jct.bd.patientapp.model.entities.Message;
import com.jct.bd.patientapp.model.entities.Patient;

public interface IBackend {
    public Void addPatient(Patient patient, final Action<String> action);
    public Void addMessage(Message message, final Action<String> action);
    public Void updatePatient(Patient patient,final Action<String> action);
}
