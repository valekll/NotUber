package edu.uga.cs.notuber;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

/**
 * An activity to show destinations utilizing fragments
 */
public class LandingActivity extends AppCompatActivity {

    // a TAG to identify logcat events
    private static final String TAG = "destinations";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "LandingActivity.onCreate()");

        super.onCreate(savedInstanceState);

        // IMPORTANT:
        // Add the back button in the ActionBar of this activity.  It will appear
        // as a left chevron (arrow tip), the regular back button.
        //
        // We can't use the Parent specification in the AndroidManifest, since
        // it would place the activity on the back stack.  Consequently, when going
        // back to the screen with the list of Android versions (using the "parent-specified"
        // back button, this would cause a recreation of the ListFragment.  The new list
        // would show the initial item (Android version) as selected, not the one we
        // actually selected when transitioning to the Android version details screen.
        //
        // However, there must be a listener added for this back button (look below).
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled( true );

        // if this call is in landscape orientation, do nothing and return,
        // as the main activity w/ ListFragment will do the work.
        // In fact, CountryListFragment will control this work.
        if( getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE ) {
            Log.d( TAG, "LandingActivity.onCreate(): in landscape mode; returning" );
            finish();
            return;
        }

        Log.d(TAG, "LandingActivity.onCreate(): in portrait mode; replacing fragments");

        LandingFragment landingFragment = new LandingFragment();
        Log.d(TAG, "LandingActivity.onCreate(): landingFragment: " + landingFragment);


        // pass on any saved data, i.e., Android version no (list index)
        landingFragment.setArguments( getIntent().getExtras() );

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // add the fragment within a transaction
        // android.R.id.content is used to obtain the view reference without having to have its id
        // it reference the root (ViewGroup) of the entire content area of an Activity
        getSupportFragmentManager().beginTransaction().replace( android.R.id.content, landingFragment ).commit();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Implement Back button listener method.
        // This method may be used for other actions from the ActionBar menu, if provided in the layout.
        int id = item.getItemId();

        // android.R.id.home is the built-in designation of the back button widget.

        if( id == android.R.id.home ) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected( item );
    }
}
