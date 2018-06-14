
/***************************************************************************************************
 * A container for Messages
 **************************************************************************************************/



package com.deneme.caulis.caulis.classes;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

public class CaulisMessage implements CaulisClasses {
    private String message;
    private String senderName;
    private String senderID;
    private String messageID;
    private long messageTime;

    //TODO: düzenlenecek
    public CaulisMessage(String message){
        this.message = message;
        this.messageTime = new Date().getTime();
    }

    public CaulisMessage(JSONObject o) throws JSONException {
        this.message = o.get("message").toString();
        this.messageID = o.get("messageID").toString();
        //this.messageTime = (long) o.get("messageTime");
    }

    public CaulisMessage(){
        this.messageTime = new Date().getTime();
    }


    public String getMessage() {
        return message;
    }

    public String getSenderName() {
        return senderName;
    }

    public String getSenderID() {
        return senderID;
    }

    public long getMessageTime() {
        return messageTime;
    }

    public String getMessageID() {
        return messageID;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setMessageID(String messageID) {
        this.messageID = messageID;
    }
}
