package edu.uga.cs.notuber;

import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

/**
 * A simple activity that show destination utilizing fragments
 */
public class LandingFragment extends Fragment {

    private String[] destinationInfo = {

    };

    private static final String TAG = "destinationInfo";

    // This method is similar to the factory method design pattern
    // to create new instances of this fragment.
    // There is a specific reason for having this method, though.  We want to send some data (DestinationIndex, here) into the
    // new fragment.  Android disallows overloaded constructors for fragments, and so we can't create a Fragment with
    // the destinationIndex as argument.  But we can use the Bundle and send the data this way.  Also, the setArguments call
    // must happen BEFORE the fragment is attached an activity.

    public static LandingFragment newInstance(int destinationIndex ) {

        Log.d( TAG,"LandingFragment.newInstance(): destinationIndex: " + destinationIndex );

        // this uses the default constructor (not defined in this class, but Java-supplied)
        LandingFragment fragment = new LandingFragment();
        Log.d(TAG, "LandingFragment.newInstance(): fragment: " + fragment);


        // save the selected list versionIndex in the new fragment's Bundle data
        // the LandingInfoFragment needs to know the version to display

        Bundle args = new Bundle();
        args.putInt( "destinationIndex", destinationIndex );
        fragment.setArguments( args );

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ) {

        View fragmentView = inflater.inflate(R.layout.fragment_landing, container, false);

        return fragmentView;
    }

    public int getShownDestinationIndex() {
        return getArguments().getInt("destinationIndex", 0 );
    }

}

