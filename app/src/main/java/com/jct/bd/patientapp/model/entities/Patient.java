package com.jct.bd.patientapp.model.entities;

import android.net.Uri;

import java.util.Date;

import com.google.firebase.database.Exclude;
import com.jct.bd.patientapp.model.entities.Type;


public class Patient {
    private String firstName;
    private String lastName;
    private String birthday;
    private String id;
    private String registrationDate;
    private String psychoId;
    private String email;
    private String password;
    private String key;
    private String fileName;
    private Type module;

    //region getters
    public String getLastName() {
        return lastName;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getBirthday() {
        return birthday;
    }
    public String getRegistrationDate() {
        return registrationDate;
    }
    public String getId() {
        return id;
    }
    public String getPsychoId() {
        return psychoId;
    }
    public String getPassword() {
        return password;
    }
    public String getEmail() {
        return email;
    }
    public Type getModule() {
        return module;
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
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
    public void setPsychoId(String psychoId) {
        this.psychoId = psychoId;
    }
    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }
    public String getKey() { return key;
    }
    public void setKey(String key) {
        this.key = key;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setModule(Type module) {
        this.module = module;
    }
    public String getFileName() {
        return fileName;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
        //endregion
    public Patient(){
        this.firstName = "";
        this.lastName = "";
        this.id = "";
        this.psychoId = "";
        this.password = "";
        this.email = "";
        this.key = "";
        this.module = Type.BEGINNER;
    }
    public Patient(String firstName, String lastName, String birthday, String id, String registrationDate, String psychoId, String password, String email,String key,String fileName, Type module){
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.id = id;
        this.registrationDate =registrationDate;
        this.psychoId = psychoId;
        this.password = password;
        this.email = email;
        this.key = key;
        this.fileName = fileName;
        this.module = module;
    }
    public static boolean IDCheck(String strID)
    {
        int[] id_12_digits = { 1, 2, 1, 2, 1, 2, 1, 2, 1 };
        int count = 0;
        if (strID == null)
            return false;
        for (int i = 0; i < 9; i++)
        {
            int num = Integer.parseInt(strID.substring(i, i+1)) * id_12_digits[i];
            if (num > 9)
                num = (num / 10) + (num % 10);
            count += num;
        }
        return (count % 10 == 0);
    }
}
