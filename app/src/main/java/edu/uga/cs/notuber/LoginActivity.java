package edu.uga.cs.notuber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * The login screen for the ride sharing app. Also the initial landing page.
 */
public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth myAuth;
    private FirebaseUser currUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //init Firebase auth
        myAuth = FirebaseAuth.getInstance();
    } //onCreate()

    @Override
    protected void onStart() {
        super.onStart();
        currUser = myAuth.getCurrentUser();
        if(currUser == null) {
            currUser = getNewUser();
        } //if
    } //onStart()

    /**
     * Gets a user to login into the app.
     * @return the new user
     */
    private FirebaseUser getNewUser() {
        FirebaseUser newUser = null;
        return newUser;
    } //getNewUser()

    /**
     * Logins into the app and sends user into landing activity.
     * @param myUser the user to login to the activity
     * @throws NullPointerException when a user has not been logged in
     */
    private void loginToLanding(FirebaseUser myUser) throws NullPointerException {
        if(myUser == null)
            throw new NullPointerException("FirebaseUser myUser may not be null.");
        Intent landingIntent = new Intent(this, LandingActivity.class);
        startActivity(landingIntent);
    } //loginToLanding

} //LoginActivity