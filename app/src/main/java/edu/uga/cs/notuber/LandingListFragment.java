package edu.uga.cs.notuber;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.ListFragment;

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
    int listingIndex = 0;
    String listingId = "";

    @Override
    public void onActivityCreated( Bundle savedInstanceState ) {
        super.onActivityCreated(savedInstanceState);

        Log.d( TAG, "LandingListFragment.onActivityCreated(): savedInstanceState: " + savedInstanceState );

        rideListings = new ArrayList<RideListing>();
        for(int i = 0; i < 100; i++) {
            RideListing ride = new RideListing();
            ride.setRideId("R" + i);
            ride.setRiderUid("LmGrrtLrLtd2fu6eEtllfhu9Zyf2");
            ride.setRideCost(100);
            ride.setOriginAddress("Origin Address");
            ride.setOriginCity("Origin City");
            ride.setDestinationAddress("Destination Address");
            ride.setDestinationCity("Destination City");
            rideListings.add(ride);
        } //for
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
        /*
        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("Titanium", "onclick activated");
                showListingInfo(i);
            } //onItemClick()
        });

         */
    } //onActivityCreated()

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Log.d("Titanium", "onlistitemclick activated");
        showListingInfo(position);
    } //onListItemClick()

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // save the list index selection
        outState.putInt( LandingFragment.LISTINGINDEX, listingIndex);
    } //onSaveInstanceState()

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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    } //onCreate()
} //LandingListFragment

