package edu.uga.cs.notuber;

/**
 * A class for users of the app.
 */
public class NotUberUser {
    private String username;
    private String email;
    private String password;

    public NotUberUser() {
    }

    public NotUberUser(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public NotUberUser(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
} //NotUberUser
