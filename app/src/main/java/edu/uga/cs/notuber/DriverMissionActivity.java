package edu.uga.cs.notuber;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DriverMissionActivity extends AppCompatActivity {

    private String rideId;

    private RideListing myRideListing;

    private NotUberUser rider;

    private Button confirmButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_mission);

        rideId = getIntent().getExtras().getString(LandingFragment.RIDEIDTAG);

        // Add the back button in the ActionBar of this activity.
        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        actionBar.setDisplayHomeAsUpEnabled(false);

        obtainRideDetails();

        confirmButton = (Button)findViewById(R.id.confirmDropOffButton);
        confirmButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent confirmIntent = new Intent(DriverMissionActivity.this, LandingMainActivity.class);
                startActivity(confirmIntent);
            }
        });

    } //onCreate()

    /**
     * Gets the ride listing's details based on the ride id.
     * @return the details
     */
    private void obtainRideDetails() {
        FirebaseDatabase fbDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myDbRef = fbDatabase.getReference("rideListings/" + rideId);
        myDbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()) {
                    Log.d("Titanium", "RideListing Snapshot exists");
                    RideListing listing = snapshot.getValue(RideListing.class);
                    myRideListing = listing;
                    if (listing != null) {
                        String details = "\nPickup Location:\n" +
                                listing.getOriginAddress() + "\n" + listing.getOriginCity() +
                                "\nDrop Off Location:\n" + listing.getDestinationAddress() + "\n" +
                                listing.getDestinationCity() + "\nRider Notes: " + listing.getRiderNotes();
                        TextView rideDetailsTextView = findViewById(R.id.rideDetailsDriverTextView);
                        rideDetailsTextView.setText(details);
                        obtainRiderDetails();
                    } //if
                } //if
            } //onDataChange()

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("Titanium", "User data read failed.");
            } //onCancelled()
        });
    } //obtainRideDetails()

    /**
     * Gets the ride's details.
     * @return the details
     */
    private void obtainRiderDetails() {
        FirebaseDatabase fbDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myDbRef = fbDatabase.getReference("users/" + myRideListing.getRiderUid());
        myDbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()) {
                    Log.d("Titanium", "RideListing Snapshot exists");
                    rider = snapshot.getValue(NotUberUser.class);
                    if (rider != null) {
                        String details = "Rider: " + rider.getFirst() + " " + rider.getLast() +
                                "\nPhone Number: " + rider.getPhoneNumber();
                        TextView riderDetailsTextView = findViewById(R.id.riderDetailsTextView);
                        riderDetailsTextView.setText(details);
                    } //if
                } //if
            } //onDataChange()

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("Titanium", "User data read failed.");
            } //onCancelled()
        });
    } //obtainRiderDetails()

} //DriverMissionActivity