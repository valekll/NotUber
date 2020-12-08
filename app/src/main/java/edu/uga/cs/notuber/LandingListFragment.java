package edu.uga.cs.notuber;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

    // indicate if this is a landscape mode with both fragments present or not
    boolean twoFragmentsActivity = false;

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
            ride.setDestinationAddress("Destination City");
            rideListings.add(ride);
        } //for
        // create a new ArrayAdapter for the list
        setListAdapter( new ArrayAdapter<>( getActivity(), android.R.layout.simple_list_item_activated_1, rideListings ) );

        // set the twoFragmentsActivity variable to true iff we are in 2 fragment (landscape) view
        View detailsFrame = getActivity().findViewById( R.id.destinationInfo );

        twoFragmentsActivity = detailsFrame != null && detailsFrame.getVisibility() == View.VISIBLE;

        // restore the saved list index selection (listing index), if available
        if(savedInstanceState != null) {
            // Restore last state for checked position.
            listingIndex = savedInstanceState.getInt(LandingFragment.LISTINGINDEX, 0 );
            listingId = savedInstanceState.getString(LandingFragment.RIDEIDTAG);
        } //if

        // set the list mode as single choice and
        getListView().setChoiceMode( ListView.CHOICE_MODE_SINGLE );
        getListView().setItemChecked( listingIndex, false );

        // if we are in 2 fragment (landscape) orientation, show the Android version information on the right side
        // This class will act as the "driver" here by displaying the details using the other fragment.
        if( twoFragmentsActivity ) {

            // display the information about the selected destination in the fragment on the right (details)
            showListingInfo( listingIndex );

            // The list in the landscape orientation may be shorter and the selected item
            // which was visible in portrait mode might be invisible (out of view)
            // i.e., "below" the end of the screen in landscape orientation.
            // To make it visible again, call smoothScrollToPosition
            getListView().smoothScrollToPosition( listingIndex );
        } //if
    } //onActivityCreated()

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        showListingInfo(position);
    } //onListItemClick()

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // save the list index selection
        outState.putInt( LandingFragment.LISTINGINDEX, listingIndex);

    } //onSaveInstanceState()

    void showListingInfo( int listingIndex ) {

        this.listingIndex = listingIndex;

        if(twoFragmentsActivity) {  // only in the 2 fragment (landscape) orientation

            getListView().setItemChecked( listingIndex, false );

            Log.d("TETRIS", "line 110 will be executed");
            // get the placeholder element (FrameLayout) in a 2 fragment (landscape) orientation layout
            LandingFragment details = (LandingFragment) getFragmentManager().findFragmentById(R.id.destinationInfo);

            if( details == null || details.getShownListingIndex() != listingIndex ) {

                Log.d("TETRIS", "line 116 will be executed");
                // obtain a new destination info fragment instance
                details = LandingFragment.newInstance(rideListings.get(listingIndex).getRideId());

                // replace the placeholder with the new fragment stance within a transaction
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace( R.id.destinationInfo, details );

                // TRANSIT_FRAGMENT_FADE means that the fragment should fade in or fade out
                fragmentTransaction.setTransition( FragmentTransaction.TRANSIT_FRAGMENT_FADE );

                // commit the transaction, i.e. make the changes
                fragmentTransaction.commit();
            } //if
        } //if
        else {
            Log.d("TETRIS", "line 132 will be executed");
            // In a 1 fragment orientation (portrait), start a new activity using an Intent, as in the previous demo app
            Intent intent = new Intent();
            intent.setClass( getActivity(), LandingInfoActivity.class );
            intent.putExtra(LandingFragment.RIDEIDTAG, rideListings.get(listingIndex).getRideId());
            intent.putExtra(LandingFragment.LISTINGINDEX, listingIndex);

            startActivity( intent );
        } //else
    } //showListingInfo()

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    } //onCreate()
} //LandingListFragment

