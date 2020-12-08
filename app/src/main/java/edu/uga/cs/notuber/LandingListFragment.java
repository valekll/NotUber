package edu.uga.cs.notuber;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.ListFragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * An activity to show destinations utilizing fragments
 */

public class LandingListFragment extends ListFragment {
    // a TAG string for logcat messages identification
    private static final String TAG = "Destinations";

    // Array of destination strings for the list fragment
    public static List<RideListing> rideListings;

    // list selection index
    private int listingIndex = 0;
    private String listingId = "";
    private Bundle savedInstanceState;

    /**
     * OnCreate method for creating the fragment.
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.savedInstanceState = savedInstanceState;
        getRideListings();
    } //onCreate()

    /**
     *
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated( Bundle savedInstanceState ) {
        super.onActivityCreated(savedInstanceState);
    } //onActivityCreated()

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // save the list index selection
        outState.putInt( LandingFragment.LISTINGINDEX, listingIndex);
    } //onSaveInstanceState()

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Log.d("Titanium", "onlistitemclick activated");
        showListingInfo(position);
    } //onListItemClick()



    /**
     * Gets the ride listings from the databse.
     */
    private void getRideListings() {
        rideListings = new ArrayList<RideListing>();
        rideListings.add(new RideListing());
        FirebaseDatabase fbDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myDbRef = fbDatabase.getReference("rideListings");
        myDbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()) {
                    Log.d("Titanium", "snapshots: " + snapshot.getChildrenCount());
                    for(DataSnapshot listingSnapshot : snapshot.getChildren()) {
                        RideListing listing = listingSnapshot.getValue(RideListing.class);
                        Log.d("Titanium", listing.toCompleteString());
                        rideListings.add(listing);
                    } //for
                } //if
                setupArrayAdapter();
            } //onDataChange()

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("Titanium", "User data read failed.");
            } //onCancelled()
        });
    } //getRideListings()

    /**
     * Sets up the array adapter.
     */
    private void setupArrayAdapter() {

        // create a new ArrayAdapter for the list
        setListAdapter( new ArrayAdapter<>( getActivity(), R.layout.list_item_for_array_adapter, rideListings ) );

        // restore the saved list index selection (listing index), if available
        if(savedInstanceState != null) {
            // Restore last state for checked position.
            listingIndex = savedInstanceState.getInt(LandingFragment.LISTINGINDEX, 0 );
            listingId = savedInstanceState.getString(LandingFragment.RIDEIDTAG);
        } //if

        // set the list mode as single choice and
        getListView().setChoiceMode( ListView.CHOICE_MODE_SINGLE );
        getListView().setItemChecked( listingIndex, true );

        // The list in the landscape orientation may be shorter and the selected item
        // which was visible in portrait mode might be invisible (out of view)
        // i.e., "below" the end of the screen in landscape orientation.
        // To make it visible again, call smoothScrollToPosition
        getListView().smoothScrollToPosition( listingIndex );
    } //setupArrayAdapter()

    /**
     * Launches an intent to allow driver to see a listing in depth.
     * @param listingIndex the listing in question
     */
    private void showListingInfo( int listingIndex ) {

        this.listingIndex = listingIndex;

        Log.d("Titanium", "listingIndex: " + listingIndex);
        Log.d("TETRIS", "line 132 will be executed");
        // In a 1 fragment orientation (portrait), start a new activity using an Intent, as in the previous demo app
        Intent intent = new Intent(getContext(), LandingInfoActivity.class);
        Log.d("TETRIS", "intent created");
        intent.putExtra(LandingFragment.RIDEIDTAG, rideListings.get(listingIndex).getRideId());
        intent.putExtra(LandingFragment.LISTINGINDEX, listingIndex);
        startActivity(intent);
    } //showListingInfo()
} //LandingListFragment

