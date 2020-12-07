package edu.uga.cs.notuber;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;


/**
 * A simple activity to show destinations utilizing fragments.
 */
public class MainActivity extends AppCompatActivity {

    private static final String DEBUG_TAG = "Destinations";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d( DEBUG_TAG, "LandingActivity.onCreate(): savedInstanceState: " + savedInstanceState );

        // this call will create the UI based on the screen in portrait orientation.
        // /res/layout/activity_country_main.xml will be used;
        // in landscape orientation /res/layout-land/activity_country_main.xml will be used
        setContentView( R.layout.activity_main);
    }


}
