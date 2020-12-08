package edu.uga.cs.notuber;

import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple activity that show destination utilizing fragments
 */
public class LandingFragment extends Fragment {

    public static final String RIDEIDTAG = "RIDEID";
    public static final String LISTINGINDEX = "LISTINGINDEX";

    private TextView listingTitleTextView;
    private TextView listingDetailsTextView;
    private Button acceptButton;

    /**
     * Generates a new instance of Landing fragment based on a ride id.
     * @param rideId the ride listing id
     * @return the fragment
     */
    public static LandingFragment newInstance(String rideId) {

        // this uses the default constructor (not defined in this class, but Java-supplied)
        LandingFragment fragment = new LandingFragment();
        // save the selected list versionIndex in the new fragment's Bundle data
        // the LandingInfoFragment needs to know the version to display

        Bundle args = new Bundle();
        args.putString(RIDEIDTAG, rideId);
        fragment.setArguments(args);

        return fragment;
    } //newInstance()

    /**
     * Creates the fragment's view
     * @param inflater the inflater
     * @param container the viewgroup
     * @param savedInstanceState the saved instance data
     * @return the view
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ) {
        View fragmentView = inflater.inflate(R.layout.fragment_landing, container, false);

        String rideId = getArguments().getString(RIDEIDTAG);
        RideListing listing = getRideListing(rideId);

        listingTitleTextView = (TextView) fragmentView.findViewById(R.id.rideListingTitleTextView);
        listingTitleTextView.setText(listing.obtainRideTitle());
        listingDetailsTextView = (TextView) fragmentView.findViewById(R.id.rideListingDetailsTextView);
        obtainRideDetails(listing);
        acceptButton = (Button) fragmentView.findViewById(R.id.acceptButton);
        return fragmentView;
    } //onCreateView()

    /**
     * Gets the listing index.
     * @return the index
     */
    public int getShownListingIndex() {
        return getArguments().getInt(LISTINGINDEX, 0 );
    } //getShownListingIndex()

    /**
     * Gets the ride listing based on the ride id
     * @param rideId the ride's id string
     * @return the ride listing
     */
    private RideListing getRideListing(String rideId) {
        for(RideListing rl : LandingListFragment.rideListings) {
            if(rl.getRideId().equals(rideId)) {
                return rl;
            } //if
        } //for
        return null;
    } //getRideListing()

    /**
     * Gets the ride listing's details based on the ride listing.
     * @param listing the listing
     * @return the details
     */
    private void obtainRideDetails(final RideListing listing) {
        FirebaseDatabase fbDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myDbRef = fbDatabase.getReference("users/" + listing.getRiderUid());
        myDbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()) {
                    Log.d("Titanium", "Snapshot exists");
                    NotUberUser rider = snapshot.getValue(NotUberUser.class);
                    if (rider != null) {
                        String details = "Passenger: " + rider.getFirst() + "\nPickup Location:\n" +
                                listing.getOriginAddress() + "\n" + listing.getOriginCity() +
                                "\nDrop Off Location:\n" + listing.getDestinationAddress() + "\n" +
                                listing.getDestinationCity() + "\nRidePoints Available: " +
                                listing.getRideCost();

                        listingDetailsTextView.setText(details);
                    } //if
                } //if
            } //onDataChange()

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("Titanium", "User data read failed.");
            } //onCancelled()
        });
    } //obtainRideDetails()

} //LandingFragment

