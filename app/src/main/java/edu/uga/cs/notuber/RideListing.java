package edu.uga.cs.notuber;

/**
 * A ride listed by a driver for a rider to see.
 */
public class RideListing {

    public String rideId;
    public String riderUid;
    public String driverUid;
    public String originAddress;
    public String destinationAddress;
    public String riderNotes;
    public String driverNotes;
    public int rideCost;
    public boolean complete;

    //constructor
    public RideListing() { complete = false; }

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

    //getter for destination address
    public String getDestinationAddress() {
        return destinationAddress;
    }

    //setter for destination address
    public void setDestinationAddress(String destinationAddress) {
        this.destinationAddress = destinationAddress;
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

    //toString method
    @Override
    public String toString() {
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
