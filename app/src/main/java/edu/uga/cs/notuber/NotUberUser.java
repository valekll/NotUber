package edu.uga.cs.notuber;

/**
 * A class for users of the app.
 */
public class NotUberUser {
    private String username;
    private String email;
    private String password;

    //constructor
    public NotUberUser() {
    }

    //constructor
    public NotUberUser(String email, String password) {
        this.email = email;
        this.password = password;
    }

    //constructor
    public NotUberUser(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    //getter for username
    public String getUsername() {
        return username;
    }

    //setter for username
    public void setUsername(String username) {
        this.username = username;
    }

    //getter for email address
    public String getEmail() {
        return email;
    }

    //setter for email address
    public void setEmail(String email) {
        this.email = email;
    }

    //getter for password
    public String getPassword() {
        return password;
    }

    //setter for password
    public void setPassword(String password) {
        this.password = password;
    }
} //NotUberUser
