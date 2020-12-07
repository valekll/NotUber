package edu.uga.cs.notuber;

import android.os.Build;

/**
 * A class for users of the app.
 */
public class NotUberUser {
    public String username;
    public String email;
    public String uid;
    public String first;
    public String last;
    public String phoneNumber;
    public int[] dob;

    //constructor
    public NotUberUser() {
    }

    //constructor
    public NotUberUser(String uid) {
        this.uid = uid;
    }

    //constructor
    public NotUberUser(String email, String uid) {
        this.email = email;
        this.uid = uid;
    }

    //constructor
    public NotUberUser(String email, String uid, String username,
                       String first, String last, String phoneNumber, int[] dob) {
        this.email = email;
        this.uid = uid;
        this.username = username;
        this.first = first;
        this.last = last;
        this.phoneNumber = phoneNumber;
        this.dob = dob;
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

    //getter for uid
    public String getUid() {
        return uid;
    }

    //setter for uid
    public void setUid(String uid) {
        this.uid = uid;
    }

    //getter for first
    public String getFirst() {
        return first;
    }

    //setter for first
    public void setFirst(String first) {
        this.first = first;
    }

    //getter for last
    public String getLast() {
        return last;
    }

    //setter for last
    public void setLast(String last) {
        this.last = last;
    }

    //getter for date of birth
    public int[] getDob() {
        return dob;
    }

    /**
     * Sets the user's date of birth
     * @param dob the date of birth
     * @throws NumberFormatException if the dob variable isn't in proper date format
     */
    public void setDob(int[] dob) throws NumberFormatException {
        if(dob.length != 3) {
            throw new NumberFormatException("Not in date time format.");
        }
        this.dob = dob;
    }

    //getter for phone number
    public String getPhoneNumber() {
        return phoneNumber;
    }

    //setter for phone number
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }



} //NotUberUser
