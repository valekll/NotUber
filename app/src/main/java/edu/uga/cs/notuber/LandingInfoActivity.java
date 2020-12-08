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
        super.onCreate(savedInstanceState);

        // Add the back button in the ActionBar of this activity.
        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        actionBar.setDisplayHomeAsUpEnabled( true );

        // if this call is in landscape orientation, do nothing and return,
        // as the main activity w/ ListFragment will do the work.
        // In fact, LandingListFragment will control this work.
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            finish();
            return;
        } //if


        LandingFragment landingFragment = new LandingFragment();

        if(getIntent().getExtras() == null) {
            Log.d("TETRIS", "bundle is null :(");
        } else {
            Log.d("TETRIS", "bundle is not null :)");
        }
        // pass on any saved data, i.e., Android version no (list index)
        landingFragment.setArguments(getIntent().getExtras());


        // add the fragment within a transaction
        // android.R.id.content is used to obtain the view reference without having to have its id
        // it reference the root (ViewGroup) of the entire content area of an Activity
        getSupportFragmentManager().beginTransaction().replace( android.R.id.content, landingFragment ).commit();
    } //onCreate()

    /**
     * Back button in toolbar
     * @param item the menu item
     * @return the item selected action
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Implement Back button listener method.
        if(item.getItemId() == android.R.id.home ) {
            onBackPressed();
            return true;
        } //if
        return super.onOptionsItemSelected( item );
    } //onOptionsItemSelected()
} //LandingInfoActivity
