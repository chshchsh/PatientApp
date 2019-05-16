package com.jct.bd.patientapp.model.entities;



public class Message {

    private String userId;
    private String timeStamp;
    private int answer1;
    private int answer2;
    private String key;
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
    public String getTimeStamp() {
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
    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }
    //endregion

    public Message(String userId, String timeStamp, int answer1, int answer2, String key, Type module){
        this.userId = userId;
        this.timeStamp = timeStamp;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.key = key;
        this.module = module;
    }
    public Message(){
        this.userId = "";
        this.timeStamp = "";
        this.answer1 = 0;
        this.answer2 = -1;
        this.key = "";
        this.module = Type.BEGINNER;
    }
}
