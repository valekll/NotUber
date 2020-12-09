package edu.uga.cs.notuber;

import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * A utility class for operations on NotUber Users
 */
public class NotUberUserDatabaseUtil {

    /**
     * Adjusts the points of a NotUberUser in the database.
     * @param uId the user's id
     * @param points the points to add/subtract (use negative values for subtraction)
     */
    public static void adjustPoints(final String uId, final int points) {
        final FirebaseDatabase fbDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myDbRef = fbDatabase.getReference("users/" + uId);
        myDbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()) {
                    Log.d("Titanium", "RideListing Snapshot exists");
                    NotUberUser myUser = snapshot.getValue(NotUberUser.class);
                    if (myUser != null) {
                        int updatedPoints = myUser.getRidePoints() + points;
                        DatabaseReference dbr = fbDatabase.getReference("users/" +
                                uId + "/ridePoints");
                        dbr.setValue(updatedPoints);
                        Log.d("Titanium", "Adjusted " + uId + " ridepoints to: " + updatedPoints);
                    } //if
                } //if
            } //onDataChange()

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("Titanium", "User data read failed.");
            } //onCancelled()
        });
    } //adjustPoints

} //RidePointsUtil
