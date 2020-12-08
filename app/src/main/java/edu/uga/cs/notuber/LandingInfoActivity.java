package edu.uga.cs.notuber;

import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

/**
 * An activity to show destinations utilizing fragments
 */
public class LandingInfoActivity extends AppCompatActivity {

    // a TAG to identify logcat events
    private static final String TAG = "destinations";

    /**
     * Creates the Landing Activity.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "LandingInfoActivity.onCreate()");

        super.onCreate(savedInstanceState);

        // Add the back button in the ActionBar of this activity.
        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        actionBar.setDisplayHomeAsUpEnabled( true );

        // if this call is in landscape orientation, do nothing and return,
        // as the main activity w/ ListFragment will do the work.
        // In fact, LandingListFragment will control this work.
        if( getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE ) {
            Log.d( TAG, "LandingInfoActivity.onCreate(): in landscape mode; returning" );
            finish();
            return;
        }

        Log.d(TAG, "LandingInfoActivity.onCreate(): in portrait mode; replacing fragments");

        LandingFragment landingFragment = new LandingFragment();
        Log.d(TAG, "LandingInfoActivity.onCreate(): landingFragment: " + landingFragment);

        // pass on any saved data, i.e., Android version no (list index)
        landingFragment.setArguments( getIntent().getExtras() );

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // add the fragment within a transaction
        // android.R.id.content is used to obtain the view reference without having to have its id
        // it reference the root (ViewGroup) of the entire content area of an Activity
        getSupportFragmentManager().beginTransaction().replace( android.R.id.content, landingFragment ).commit();
    }

    /**
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Implement Back button listener method.
        // This method may be used for other actions from the ActionBar menu, if provided in the layout.
        // android.R.id.home is the built-in designation of the back button widget.
        if(item.getItemId() == android.R.id.home ) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected( item );
    }
}
