package edu.uga.cs.notuber;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class AddRideActivity extends AppCompatActivity {

    public static final String RIDEID = "RIDEID";

    private FirebaseAuth myAuth;

    private TextView originAddressTextView;
    private TextView originCityTextView;
    private TextView destinationAddressTextView;
    private TextView destinationCityTextView;
    private TextView rideCostTextView;
    private TextView riderNotesTextView;

    private Button requestRideButton;

    private RideListing myRideListing;

    /**
     * onCreate to request a ride.
     * @param savedInstanceState the saved instance state of this activity.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ride);
        myAuth = FirebaseAuth.getInstance();

        // Add the back button in the ActionBar of this activity.
        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        actionBar.setDisplayHomeAsUpEnabled(true);

        //init fields
        originAddressTextView = (TextView)findViewById(R.id.originAddressTextView);
        originCityTextView = (TextView)findViewById(R.id.originCityTextView);
        destinationAddressTextView = (TextView)findViewById(R.id.destinationAddressTextView);
        destinationCityTextView = (TextView)findViewById(R.id.destinationCityTextView);
        rideCostTextView = (TextView)findViewById(R.id.rideCostTextView);
        riderNotesTextView = (TextView)findViewById(R.id.riderNotesTextView);

        //setup button
        requestRideButton = (Button)findViewById(R.id.requestRideButton);
        requestRideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestRide();
            } //onClick()
        });
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

    /**
     * Requests a ride and posts a listing.
     */
    private void requestRide() {
        if(checkForEmptyFields()) {
            //make toast
            Toast myMessage = Toast.makeText(AddRideActivity.this,
                    "Requesting ride!", Toast.LENGTH_SHORT);
            //set toast colors to be more visible
            View messageView = myMessage.getView();
            messageView.setBackgroundColor(Color.GRAY);
            TextView messageTextView = (TextView)myMessage.getView()
                    .findViewById(android.R.id.message);
            messageTextView.setTextColor(Color.WHITE);
            myMessage.show();

            //make ride listing
            myRideListing = new RideListing();
            myRideListing.setRideId(UUID.randomUUID().toString()); //generate random unique id
            myRideListing.setRiderUid(myAuth.getCurrentUser().getUid());
            myRideListing.setOriginAddress(originAddressTextView.getText().toString());
            myRideListing.setOriginCity(originCityTextView.getText().toString());
            myRideListing.setDestinationAddress(destinationAddressTextView.getText().toString());
            myRideListing.setDestinationCity(destinationCityTextView.getText().toString());
            Log.d("Titanium", rideCostTextView.getText().toString()); // Do not delete line
            String rideCostAsString = rideCostTextView.getText().toString();
            Log.d("Titanium", rideCostAsString); // Do not delete line
            myRideListing.setRideCost(Integer.parseInt(rideCostAsString));
            myRideListing.setRiderNotes(riderNotesTextView.getText().toString());

            //put in Database
            FirebaseDatabase fbDatabase = FirebaseDatabase.getInstance();
            DatabaseReference myDbRef = fbDatabase.getReference("rideListings/" +
                    myRideListing.getRideId());
            myDbRef.setValue(myRideListing);

            Intent awaitRideIntent = new Intent(this, AwaitRideActivity.class);
            awaitRideIntent.putExtra(RIDEID, myRideListing.getRideId());
            startActivity(awaitRideIntent);
        } //if
        else {
            //make toast
            Toast myMessage = Toast.makeText(AddRideActivity.this,
                    "Please complete form", Toast.LENGTH_SHORT);
            //set toast colors to be more visible
            View messageView = myMessage.getView();
            messageView.setBackgroundColor(Color.GRAY);
            TextView messageTextView = (TextView)myMessage.getView()
                    .findViewById(android.R.id.message);
            messageTextView.setTextColor(Color.WHITE);
            myMessage.show();
        } //else
    } //requestRide()

    /**
     * Checks for empty fields.
     * @return true if no empty fields
     */
    private boolean checkForEmptyFields() {
        if(originAddressTextView.getText().toString().equals("") ||
                originCityTextView.getText().toString().equals("") ||
                destinationCityTextView.getText().toString().equals("") ||
                destinationAddressTextView.getText().toString().equals("") ||
                rideCostTextView.getText().toString().equals("")
        ) {return false;}
        return true;
    } //checkForEmptyFields

} //AddRideActivity

