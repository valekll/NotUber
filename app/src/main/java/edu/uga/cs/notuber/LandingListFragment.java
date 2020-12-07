package edu.uga.cs.notuber;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.ListFragment;

/**
 * An activity to show destinations utilizing fragments
 */

public class LandingListFragment extends ListFragment {
    // a TAG string for logcat messages identification
    private static final String TAG = "Destinations";

    // Array of destination strings for the list fragment
    private String[] destinations = {
            "Destination 1",
            "Destination 2",
            "Destination 3",
            "Destination 4",
            "Destination 5"
    };

    // indicate if this is a landscape mode with both fragments present or not
    boolean twoFragmentsActivity = false;

    // list selection index
    int destinationIndex = 0;

    @Override
    public void onActivityCreated( Bundle savedInstanceState ) {

        super.onActivityCreated(savedInstanceState);

        Log.d( TAG, "LandingListFragment.onActivityCreated(): savedInstanceState: " + savedInstanceState );

        // create a new ArrayAdapter for the list
        setListAdapter( new ArrayAdapter<>( getActivity(), android.R.layout.simple_list_item_activated_1, destinations ) );

        // set the twoFragmentsActivity variable to true iff we are in 2 fragment (landscape) view
        View detailsFrame = getActivity().findViewById( R.id.destinationInfo );
        Log.d( TAG, "LandingListFragment.onActivityCreated(): detailsFrame: " + detailsFrame );

        twoFragmentsActivity = detailsFrame != null && detailsFrame.getVisibility() == View.VISIBLE;

        // restore the saved list index selection (Android version no), if available
        if( savedInstanceState != null ) {
            // Restore last state for checked position.
            destinationIndex = savedInstanceState.getInt("destinationSelection", 0 );
            Log.d( TAG, "LandingListFragment.onActivityCreated(): restored versionIndex: " + destinationIndex );
        }

        // set the list mode as single choice and
        getListView().setChoiceMode( ListView.CHOICE_MODE_SINGLE );
        getListView().setItemChecked( destinationIndex, true );

        // if we are in 2 fragment (landscape) orientation, show the Android version information on the right side
        // This class will act as the "driver" here by displaying the details using the other fragment.
        if( twoFragmentsActivity ) {

            // display the information about the selected destination in the fragment on the right (details)
            showDestinationInfo( destinationIndex );

            // The list in the landscape orientation may be shorter and the selected item
            // which was visible in portrait mode might be invisible (out of view)
            // i.e., "below" the end of the screen in landscape orientation.
            // To make it visible again, call smoothScrollToPosition
            getListView().smoothScrollToPosition( destinationIndex );
        }
    }
    @Override
    public void onListItemClick( ListView l, View v, int position, long id ) {
        // on a click on a list item, show the selected Android version info
        // store the list view and selection for coming back to the list (using the back button)
        //firstVisibleVersionIndex = l.getFirstVisiblePosition();
        //versionIndex = position;
        showDestinationInfo( position );
    }

    @Override
    public void onSaveInstanceState( Bundle outState ) {
        super.onSaveInstanceState(outState);

        // save the list index selection
        outState.putInt( "destinationSelection", destinationIndex);
        Log.d( TAG, "LandingListFragment.onSaveInstanceState(): saved destinationIndex: " + destinationIndex );
    }

    void showDestinationInfo( int destinationIndex ) {

        this.destinationIndex = destinationIndex;

        if( twoFragmentsActivity ) {  // only in the 2 fragment (landscape) orientation

            getListView().setItemChecked( destinationIndex, true );

            // get the placeholder element (FrameLayout) in a 2 fragment (landscape) orientation layout
            LandingFragment details = (LandingFragment) getFragmentManager().findFragmentById( R.id.destinationInfo );

            Log.d( TAG, "LandingListFragment.showDestinationInfo(): details: " + details );

            if( details == null || details.getShownDestinationIndex() != destinationIndex ) {

                // obtain a new destination info fragment instance
                details = LandingFragment.newInstance( destinationIndex );

                // replace the placeholder with the new fragment stance within a transaction
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace( R.id.destinationInfo, details );

                // TRANSIT_FRAGMENT_FADE means that the fragment should fade in or fade out
                fragmentTransaction.setTransition( FragmentTransaction.TRANSIT_FRAGMENT_FADE );

                // commit the transaction, i.e. make the changes
                fragmentTransaction.commit();
            }
        }
        else {
            // In a 1 fragment orientation (portrait), start a new activity using an Intent, as in the previous demo app
            Intent intent = new Intent();
            intent.setClass( getActivity(), LandingActivity.class );
            intent.putExtra("destinationIndex", destinationIndex);

            startActivity( intent );
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d( TAG, "LandingListFragment.onCreate()" );

        // IMPORTANT: this method call will prevent this fragment from being destroyed when
        // recreating the hosting activity. Consequently, the list will be retained.
        //setRetainInstance( true );
    }

}

