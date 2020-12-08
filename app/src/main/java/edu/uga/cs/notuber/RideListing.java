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
 * A ride listed by a driver for a rider to see.
 */
public class RideListing {

    public String rideId;
    public String riderUid;
    public String driverUid;
    public String originAddress;
    public String originCity;
    public String destinationAddress;
    public String destinationCity;
    public String riderNotes;
    public String driverNotes;
    public int distance;
    public int rideCost;
    public boolean complete;

    //constructor
    public RideListing() {
        rideId = "";
        riderUid = "";
        driverUid = "";
        originAddress = "";
        originCity = "";
        destinationAddress = "";
        destinationCity = "";
        riderNotes = "";
        driverNotes = "";
        complete = false;
    }

    //getter for ride's id
    public String getRideId() {
        return rideId;
    }

    //setter for ride's id
    public void setRideId(String rideId) {
        this.rideId = rideId;
    }

    //getter for rider's user id
    public String getRiderUid() {
        return riderUid;
    }

    //setter for rider's user id
    public void setRiderUid(String riderUid) {
        this.riderUid = riderUid;
    }

    //getter for driver's user id
    public String getDriverUid() {
        return driverUid;
    }

    //setter for driver's user id
    public void setDriverUid(String driverUid) {
        this.driverUid = driverUid;
    }

    //getter for origin address
    public String getOriginAddress() {
        return originAddress;
    }

    //setter for origin address
    public void setOriginAddress(String originAddress) {
        this.originAddress = originAddress;
    }

    //getter for origin city
    public String getOriginCity() {
        return originCity;
    }

    //setter for origin city
    public void setOriginCity(String originCity) {
        this.originCity = originCity;
    }

    //getter for destination address
    public String getDestinationAddress() {
        return destinationAddress;
    }

    //setter for destination address
    public void setDestinationAddress(String destinationAddress) {
        this.destinationAddress = destinationAddress;
    }

    //getter for destination city
    public String getDestinationCity() {
        return destinationCity;
    }

    //setter for destination city
    public void setDestinationCity(String destinationCity) {
        this.destinationCity = destinationCity;
    }

    //getter for rider's notes
    public String getRiderNotes() {
        return riderNotes;
    }

    //setter for rider's notes
    public void setRiderNotes(String riderNotes) {
        this.riderNotes = riderNotes;
    }

    //getter for driver's notes
    public String getDriverNotes() {
        return driverNotes;
    }

    //setter for driver's notes
    public void setDriverNotes(String driverNotes) {
        this.driverNotes = driverNotes;
    }

    //getter for ride distance
    public int getDistance() {
        return distance;
    }

    //setter for ride distance
    public void setDistance(int distance) {
        this.distance = distance;
    }

    //getter for ride cost
    public int getRideCost() {
        return rideCost;
    }

    //setter for ride cost
    public void setRideCost(int rideCost) {
        this.rideCost = rideCost;
    }

    //getter for ride status
    public boolean isComplete() {
        return complete;
    }

    //setter for ride status
    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    /**
     * Getter for the ride's title
     * @return the title
     */
    public String obtainRideTitle() {
        return originCity + " to " + destinationCity;
    } //obtainRideTitle()

    /**
     * Getter for the ride's details
     * @return the details
     */
    public String obtainRideDetails() {
        final NotUberUser[] rider = {null};
        DatabaseReference myDbRef = FirebaseDatabase.getInstance().getReference("users/" + riderUid);
        myDbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                rider[0] = snapshot.getValue(NotUberUser.class);
                if(rider[0] != null) {
                    Log.d("Titanium", rider[0].toString());
                } //if
            } //onDataChange()

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("Titanium", "User data read failed.");
            } //onCancelled()
        });
        String details = "Passenger: " + rider[0].first + "\nPickup Location:\n" + originAddress +
                "\n" + originCity + "\nDrop Off Location:\n" + destinationAddress + "\n" +
                destinationCity + "\nRidePoints Available: " + rideCost;
        return details;
    } //obtainRideDetails()

    //toString method
    @Override
    public String toString() {
        return rideId;
    }

    //another toString method
    public String toCompleteString() {
        return "RideListing{" +
                "rideId='" + rideId + '\'' +
                ", riderUid='" + riderUid + '\'' +
                ", driverUid='" + driverUid + '\'' +
                ", originAddress='" + originAddress + '\'' +
                ", destinationAddress='" + destinationAddress + '\'' +
                ", rideCost=" + rideCost +
                ", complete=" + complete +
                '}';
    }

} //RideListing
