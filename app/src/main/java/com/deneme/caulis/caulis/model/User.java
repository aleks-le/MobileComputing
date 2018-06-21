package com.deneme.caulis.caulis.model;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class User {

    private String eMail;
    private String password;
    private String userID;
    private String name;
    private String surName;
    private String userMailValidFormat;
    private String notAllowedChars = ("@./-_");

    // ...
    
    public User (String eMail, String password){
        this.eMail = eMail;
        this.password = password;
    }

    public User (String eMail, String password, String name, String surName){
        this.eMail = eMail;
        this.password = password;
        this.name = name;
        this.surName = surName;
        this.setUserMailValidFormat();
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public void setUserMailValidFormat(){
        StringBuilder a = new StringBuilder();
        char[] b = this.eMail.toCharArray();
        for(char i:b){
            if(this.notAllowedChars.indexOf(i) == -1){
                a.append(i);

            }
        }
        this.userMailValidFormat = a.toString();
    }

    public String getUserMailValidFormat() {
        return userMailValidFormat;
    }
}
