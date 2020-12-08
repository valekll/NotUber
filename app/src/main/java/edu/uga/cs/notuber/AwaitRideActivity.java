package edu.uga.cs.notuber;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class AwaitRideActivity extends AppCompatActivity {

    private FirebaseAuth myAuth;
    private TextView rideIdTextView;
    private String rideId;
    private Button requestRideButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_await_ride);
        myAuth = FirebaseAuth.getInstance();

        // Add the back button in the ActionBar of this activity.
        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        actionBar.setDisplayHomeAsUpEnabled(true);

        //rideId = getIntent().getExtras().getString(AddRideActivity.RIDEID);
        //rideIdTextView = (TextView)findViewById(R.id.rideIdTextView);
        //rideIdTextView.setText(rideId);


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
} //AwaitRideActivity