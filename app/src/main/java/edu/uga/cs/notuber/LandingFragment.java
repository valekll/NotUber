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
        // the CountryInfoFragment needs to know the version to display
        Bundle args = new Bundle();
        args.putInt( "destinationIndex", destinationIndex );
        fragment.setArguments( args );

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ) {

        Log.d( TAG, "LandingFragment.onCreateView()" );

        // Programmatically, create a scrollable TextView to show the Android version information
        ScrollView scroller = new ScrollView( getActivity()) ;
        TextView textView = new TextView( getActivity() );
        ImageView imageView = new ImageView(getActivity());

        // Set the padding for the new TextView
        // These padding attributes are normally defined in the XML file
        // here, they are set programmatically.
        int padding = (int) TypedValue.applyDimension( TypedValue.COMPLEX_UNIT_DIP, 12, getActivity().getResources().getDisplayMetrics() );
        textView.setPadding( padding, padding, padding, padding );

        // set the text size
        textView.setTextSize( TypedValue.COMPLEX_UNIT_SP, 18f );

        // show the destination info
        //textView.setText( destinationInfo[ getShownDestinationIndex() ] );

       //if (getShownDestinationIndex() == 0) {

       // } else if (getShownDestinationIndex() == 1) {

        //} else if (getShownDestinationIndex() == 2) {

        //
        //} else {

        //} // if else

        //LinearLayout picture = new LinearLayout(getActivity());

       // picture.addView(textView);
        //picture.addView(imageView);
        //.setOrientation(LinearLayout.VERTICAL);
        //scroller.addView(picture);


        return scroller;
    }
    public int getShownDestinationIndex() {
        return getArguments().getInt("destinationIndex", 0 );
    }
}

