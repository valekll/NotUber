package edu.uga.cs.notuber;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class AddRideActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ride);

        // Add the back button in the ActionBar of this activity.
        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        actionBar.setDisplayHomeAsUpEnabled( true );
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
} //AddRideActivity

