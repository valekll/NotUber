package edu.uga.cs.notuber;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;


/**
 * A simple activity to show destinations utilizing fragments.
 */
public class LandingMainActivity extends AppCompatActivity {

    private static final String DEBUG_TAG = "Destinations";

    private FirebaseAuth myAuth;
    private Button logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d( DEBUG_TAG, "LandingActivity.onCreate(): savedInstanceState: " + savedInstanceState );

        // this call will create the UI based on the screen in portrait orientation.
        // /res/layout/activity_country_main.xml will be used;
        // in landscape orientation /res/layout-land/activity_country_main.xml will be used
        setContentView( R.layout.activity_landing_main);

        myAuth = FirebaseAuth.getInstance();
        logoutButton = (Button)findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myAuth.signOut();
                Intent loginIntent = new Intent(LandingMainActivity.this,
                        LoginActivity.class);
                startActivity(loginIntent);
            } //onClick()
        });
    } //onCreate()


} //LandingActivityMain
