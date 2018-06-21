package com.deneme.caulis.caulis.model;

public class UserCollector {
    private static UserCollector INSTANCE = null;
    private User user;

    public static UserCollector getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UserCollector();
        }
        return INSTANCE;
    }

    private UserCollector() {

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
