// -----------------------------------------------------
// Assignment 2
// Question: Part 1
// Written by: Nathan Grenier, 40250986
// COMP 249
// Due Date: Febuary 24, 2023
// 
// This is the Metro class. It extends Train.
// -----------------------------------------------------

package Part1.package3;

import javax.print.attribute.standard.MediaSize.Other;

import Part1.package2.Train;

/**
 * <p>
 * The Metro class describes Metro objects. These objects have a new attribute
 * named stopCount.
 * </p>
 * 
 * @author Nathan Grenier
 * @version 1.0
 * 
 */
public class Metro extends Train {
    protected int stopCount;

    public Metro() {
        super();
        this.stopCount = 0;
    }

    public Metro(int wheelCount, double maxSpeed, int vehicleCount, String startStation, String endStation,
            int stopCount) {
        super();
        this.stopCount = stopCount;
    }

    public Metro(Metro obj) {
        this(obj.wheelCount, obj.maxSpeed, obj.vehicleCount, obj.startStation, obj.endStation, obj.stopCount);
    }

    /**
     * Displays the attributes of a Metro object to the console.
     * 
     * @return String
     */
    @Override
    public String toString() {
        return "Metro{" + "stopCount=" + this.stopCount + "} " + super.toString();
    }

    /**
     * Verifies if 2 objects are of the same type and that their attributes are all
     * equal.
     * 
     * @param obj
     * @return boolean
     */
    @Override
    public boolean equals(Object obj) {
        if (super.equals(obj)) {
            Metro otherObj = (Metro) obj;
            return stopCount == otherObj.stopCount;
        }
        return false;
    }

    /**
     * Returns a copy of the Metro object. The copy is made using the Metro's class
     * copy constuctor.
     * 
     * @return Metro
     */
    @Override
    public Metro clone() {
        return new Metro(this);
    }

    /**
     * Gets the number of stops of a Metro.
     * 
     * @return int
     */
    public int getStopCount() {
        return this.stopCount;
    }

    /**
     * Sets the number of stops of a Metro.
     * 
     * @param stopCount
     */
    public void setStopCount(int stopCount) {
        this.stopCount = stopCount;
    }
}
