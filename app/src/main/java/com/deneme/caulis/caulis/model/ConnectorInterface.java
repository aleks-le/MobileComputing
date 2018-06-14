package com.deneme.caulis.caulis.model;

import android.app.Activity;

import com.deneme.caulis.caulis.classes.CaulisClasses;
import com.deneme.caulis.caulis.classes.CaulisEvent;
import com.deneme.caulis.caulis.classes.CaulisGroup;
import com.deneme.caulis.caulis.classes.CaulisList;
import com.deneme.caulis.caulis.classes.CaulisMessage;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public interface ConnectorInterface {

    interface LoginCallback {
        void userLoggedIn(User user);
    }


    User login(String login, String password, final Activity activity);

    boolean register(String login, String password, final Activity activity);

    void sendMessage(CaulisMessage message);

    void createGroup(CaulisGroup group);

    void createEvent(CaulisEvent event);

    void setMessageListener(final CaulisList list, String groupID);

    void setGroupListener(final CaulisList list);



    //gidici
    public String randomString();



}
