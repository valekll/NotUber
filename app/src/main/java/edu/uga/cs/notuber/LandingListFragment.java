package edu.uga.cs.notuber;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.ListFragment;
public class LandingListFragment extends ListFragment {

    // a TAG string for logcat messages identification
    private static final String TAG = "rideRequests";

    private String[] destination = {
            "Destination 1",
            "Destination 2",
            "Destination 3",
            "Destination 4",
            "Destination 5"
    };

    // this indicate if this is a landscape mode with both fragments present or not.
}
