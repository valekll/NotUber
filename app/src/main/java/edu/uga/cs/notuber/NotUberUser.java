package edu.uga.cs.notuber;

import android.os.Build;

import java.util.Arrays;

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
    public int day;
    public int month;
    public int year;
    public int ridePoints;

    //constructor
    public NotUberUser() {
        this.ridePoints = 1000;
    }

    //constructor
    public NotUberUser(String uid) {
        this.uid = uid;
        this.ridePoints = 1000;
    }

    //constructor
    public NotUberUser(String email, String uid) {
        this.email = email;
        this.uid = uid;
        this.ridePoints = 1000;
    }

    //constructor
    public NotUberUser(String email, String uid, String username,
                       String first, String last, String phoneNumber, int day, int month, int year) {
        this.email = email;
        this.uid = uid;
        this.username = username;
        this.first = first;
        this.last = last;
        this.phoneNumber = phoneNumber;
        this.day = day;
        this.month = month;
        this.year = year;
        this.ridePoints = 1000;
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

    //getter day
    public int getDay() {
        return day;
    }

    //setter day
    public void setDay(int day) {
        this.day = day;
    }

    //getter month
    public int getMonth() {
        return month;
    }

    //setter month
    public void setMonth(int month) {
        this.month = month;
    }

    //getter year
    public int getYear() {
        return year;
    }

    //setter year
    public void setYear(int year) {
        this.year = year;
    }

    //getter ride points
    public int getRidePoints() {
        return ridePoints;
    }

    //getter ride points
    public void setRidePoints(int ridePoints) {
        this.ridePoints = ridePoints;
    }

    //getter for phone number
    public String getPhoneNumber() {
        return phoneNumber;
    }

    //setter for phone number
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    //toString method
    @Override
    public String toString() {
        return "NotUberUser{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", uid='" + uid + '\'' +
                ", first='" + first + '\'' +
                ", last='" + last + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", day=" + day +
                ", month=" + month +
                ", year=" + year +
                '}';
    }
} //NotUberUser
