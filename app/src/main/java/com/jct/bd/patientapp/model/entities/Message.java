package com.jct.bd.patientapp.model.entities;

import java.sql.Time;
import com.jct.bd.patientapp.model.entities.Type;

public class Message {

    private String userId;
    private Time timeStamp;
    private int answer1;
    private int answer2;
    private Type module; // should be 1, 2, 3. the number describes the module that the patient in...

    //region getters
    public int getAnswer1() {
        return answer1;
    }
    public int getAnswer2() {
        return answer2;
    }
    public Type getModule() {
        return module;
    }
    public String getUserId() {
        return userId;
    }
    public Time getTimeStamp() {
        return timeStamp;
    }
    //endregion
    //region setters
    public void setAnswer1(int answer1) {
        this.answer1 = answer1;
    }
    public void setAnswer2(int answer2) {
        this.answer2 = answer2;
    }
    public void setModule(Type module) {
        this.module = module;
    }
    public void setTimeStamp(Time timeStamp) {
        this.timeStamp = timeStamp;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    //endregion

    public Message(String userId, Time timeStamp, int answer1, int answer2, Type module){
        this.userId = userId;
        this.timeStamp = timeStamp;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.module = module;
    }
}
