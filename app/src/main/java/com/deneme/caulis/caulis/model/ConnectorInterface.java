package com.deneme.caulis.caulis.model;

import android.app.Activity;

public interface ConnectorInterface {

    interface LoginCallback {
        void userLoggedIn(User user);
    }


    User login(String login, String password, final Activity activity);

    boolean register(String login, String password, final Activity activity);



}
