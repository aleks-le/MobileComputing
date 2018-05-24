package com.deneme.caulis.caulis.model;

import org.json.JSONException;
import org.json.JSONObject;

public class User {

    private String eMail;
    private String password;
    // ...


    public User (){

    }

    public User (String eMail, String password){
        this.eMail = eMail;
        this.password = password;
    }

    public JSONObject toJson () throws JSONException {
        JSONObject userJSON = new JSONObject();
        userJSON.put("email", this.eMail);
        userJSON.put("password",this.password);
        return userJSON;
    }

    public String getUserData(){
        JSONObject userDataJSON = null;
        String userData = null;
        try {
            userDataJSON = toJson();
            userData = userDataJSON.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //string boş ise ekrana log verebilir
        return  userData;
    }

    public void updateUserWithJSON(String s){
        JSONObject userData = null;
        try {
            userData = new JSONObject(s);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (userData != null){
            try {
                this.eMail = userData.getString("email");
                this.password = userData.getString("password");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }


}
