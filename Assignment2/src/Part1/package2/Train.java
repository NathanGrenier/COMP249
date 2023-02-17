// -----------------------------------------------------
// Assignment 2
// Question: Part 1
// Written by: Nathan Grenier, 40250986
// COMP 249
// Due Date: Febuary 24, 2023
// 
// This is the Train class. It extends WheeledTransportation.
// -----------------------------------------------------

package Part1.package2;

import Part1.package1.WheeledTransportation;

/**
 * <p>
 * The Train class describes Train objects. These objects have a certain number
 * of vehicles/train cars, the name of a start station, and the name of an end
 * station.
 * </p>
 * 
 * @author Nathan Grenier
 * @version 1.0
 */
public class Train extends WheeledTransportation {
    protected int vehicleCount;
    protected String startStation;
    protected String endStation;

    public Train() {
        super();
        this.vehicleCount = 0;
        this.startStation = "undefined";
        this.endStation = "undefined";
    }

    public Train(int wheelCount, double maxSpeed, int vehicleCount, String startStation, String endStation) {
        super(wheelCount, maxSpeed);
        this.vehicleCount = vehicleCount;
        this.startStation = startStation;
        this.endStation = endStation;
    }

    public Train(Train obj) {
        this(obj.wheelCount, obj.maxSpeed, obj.vehicleCount, obj.startStation, obj.endStation);
    }

    /**
     * Displays the attributes of a train object to the console.
     * 
     * @return String
     */
    @Override
    public String toString() {
        return "Train{" + "vehicleCount=" + this.vehicleCount + ", startStation='" + this.startStation
                + "', endStation='"
                + this.endStation + "'} " + super.toString();
    }

    /**
     * Verifies if 2 objects are of the same type and if they have the same values
     * for all of their attributes.
     * 
     * @param obj
     * @return boolean
     */
    @Override
    public boolean equals(Object obj) {
        if (super.equals(obj)) {
            Train otherObj = (Train) obj;
            return (vehicleCount == otherObj.vehicleCount && startStation.equals(otherObj.startStation)
                    && endStation.equals(endStation));
        }
        return false;
    }

    /**
     * Returns a copy of the train object. The copy is made using the Train class'
     * copy constuctor.
     * 
     * @return Train
     */
    @Override
    public Train clone() {
        return new Train(this);
    }

    /**
     * Gets the number of vehicles of the Train object.
     * 
     * @return int
     */
    public int getVehicleCount() {
        return this.vehicleCount;
    }

    /**
     * Gets the name of the start station of the Train object.
     * 
     * @return String
     */
    public String getStartStation() {
        return this.startStation;
    }

    /**
     * Gets the name of the end station of the Train object.
     * 
     * @return String
     */
    public String getEndStation() {
        return this.endStation;
    }

    /**
     * Sets the number of vehicles of the Train object.
     * 
     * @param vehicleCount
     */
    public void setVehicleCount(int vehicleCount) {
        this.vehicleCount = vehicleCount;
    }

    /**
     * Sets the name of the start station of the Train object.
     * 
     * @param startStation
     */
    public void setStartStation(String startStation) {
        this.startStation = startStation;
    }

    /**
     * Sets the name of the end station of the Train object.
     * 
     * @param endStation
     */
    public void setEndStation(String endStation) {
        this.endStation = endStation;
    }
}
