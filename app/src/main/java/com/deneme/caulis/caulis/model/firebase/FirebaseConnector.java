package com.deneme.caulis.caulis.model.firebase;

import com.deneme.caulis.caulis.model.CalendarInterface;
import com.deneme.caulis.caulis.model.User;
import com.google.firebase.auth.FirebaseAuth;

public class FirebaseConnector implements CalendarInterface {

    private static FirebaseConnector INSTANCE = null;

    public static FirebaseConnector getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new FirebaseConnector();
        }
        return INSTANCE;
    }

    private FirebaseAuth mAuth;

    private FirebaseConnector() {
        // Connect to Fırebase
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public User login(String login, String password) {
        // check user
        return null;
    }

    @Override
    public boolean register(String login, String password) {
// mAuth.createUserWithEmailAndPassword(email, password)...
        return false;
    }
}
