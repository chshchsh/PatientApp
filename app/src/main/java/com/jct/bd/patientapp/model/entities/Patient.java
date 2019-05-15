package com.jct.bd.patientapp.controller.model.entities;

import java.sql.Time;
import java.util.Date;

import com.jct.bd.patientapp.controller.model.entities.Type;


public class Patient {
    private String firstName;
    private String lastName;
    private Date birthday;
    private String id;
    private Date registrationDate;
    private String psychoId;

    //region getters
    public String getLastName() {
        return lastName;
    }
    public String getFirstName() {
        return firstName;
    }
    public Date getBirthday() {
        return birthday;
    }
    public Date getRegistrationDate() {
        return registrationDate;
    }
    public String getId() {
        return id;
    }
    public String getPsychoId() {
        return psychoId;
    }
    //endregion
    // region setters
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setId(String id) {
        this.id = id;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    public void setPsychoId(String psychoId) {
        this.psychoId = psychoId;
    }
    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }
    //endregion

    public Patient(String firstName, String lastName, Date birthday, String id, Date registrationDate, String psychoId){
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.id = id;
        this.registrationDate =registrationDate;
        this.psychoId = psychoId;
    }
}
