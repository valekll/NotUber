package edu.uga.cs.notuber;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AwaitRideActivity extends AppCompatActivity {

    private FirebaseAuth myAuth;

    private RideListing myRideListing;
    private NotUberUser driver;

    private String rideId;

    private Button confirmButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_await_ride);
        myAuth = FirebaseAuth.getInstance();

        // Add the back button in the ActionBar of this activity.
        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        actionBar.setDisplayHomeAsUpEnabled(false);

        rideId = getIntent().getExtras().getString(AddRideActivity.RIDEID);
        obtainRideDetails();
        confirmButton = (Button)findViewById(R.id.confirmArrivalButton);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(driver == null) {
                    //make toast
                    Toast myMessage = Toast.makeText(AwaitRideActivity.this,
                            "Please wait for a driver.", Toast.LENGTH_SHORT);
                    //set toast colors to be more visible
                    View messageView = myMessage.getView();
                    messageView.setBackgroundColor(Color.GRAY);
                    TextView messageTextView = (TextView)myMessage.getView()
                            .findViewById(android.R.id.message);
                    messageTextView.setTextColor(Color.WHITE);
                    myMessage.show();
                } //if
                else {
                    FirebaseDatabase fbDatabase = FirebaseDatabase.getInstance();
                    DatabaseReference listingCompletion = fbDatabase.getReference("rideListings/" +
                            myRideListing.getRideId() + "/complete");
                    listingCompletion.setValue(true);
                    NotUberUserDatabaseUtil.adjustPoints(myRideListing.getDriverUid(), myRideListing.getRideCost());
                    Intent confirmIntent = new Intent(AwaitRideActivity.this, LandingMainActivity.class);
                    startActivity(confirmIntent);
                } //else
            } //onClick()
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
                                listing.getDestinationCity() + "\nDriver Notes: " + listing.getDriverNotes();
                        TextView rideDetailsTextView = findViewById(R.id.rideDetailsRiderTextView);
                        rideDetailsTextView.setText(details);
                        obtainDriverDetails();
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
     * Gets the ride driver's details.
     * @return the details
     */
    private void obtainDriverDetails() {
        FirebaseDatabase fbDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myDbRef = fbDatabase.getReference("users/" + myRideListing.getDriverUid());
        myDbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()) {
                    Log.d("Titanium", "RideListing Snapshot exists");
                    driver = snapshot.getValue(NotUberUser.class);
                    if (driver != null) {
                        String details = "Driver: " + driver.getFirst() + " " + driver.getLast() +
                                "\nPhone Number: " + driver.getPhoneNumber();
                        TextView driverDetailsTextView = findViewById(R.id.driverDetailsTextView);
                        driverDetailsTextView.setText(details);
                    } //if
                } //if
            } //onDataChange()

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("Titanium", "User data read failed.");
            } //onCancelled()
        });
    } //obtainDriverDetails()

} //AwaitRideActivity