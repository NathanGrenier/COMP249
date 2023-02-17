// -----------------------------------------------------
// Assignment 2
// Question: Part 1
// Written by: Nathan Grenier, 40250986
// COMP 249
// Due Date: Febuary 24, 2023
// 
// This is the Monowheel class. It extends WheeledTransportation.
// -----------------------------------------------------

package Part1.package4;

import Part1.package1.WheeledTransportation;

/**
 * The Monowheel class describes Monowheel objects. It extends the
 * WheeledTransportation class. It has a new attribute named maxWeight.
 * 
 * @author Nathan Grenier
 * @version 1.0
 */
public class Monowheel extends WheeledTransportation {
    private double maxWeight;

    public Monowheel() {
        super();
        this.maxWeight = 0.0;
    }

    public Monowheel(int wheelCount, double maxSpeed, double maxWeight) {
        super(wheelCount, maxSpeed);
        this.maxWeight = maxWeight;
    }

    public Monowheel(Monowheel obj) {
        this(obj.wheelCount, obj.maxSpeed, obj.maxWeight);
    }

    /**
     * Displays the attributes of a Monowheel object to the console.
     * 
     * @return String
     */
    @Override
    public String toString() {
        return "Monowheel{" + "maxWeight=" + this.maxWeight + "} " + super.toString();
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
            Monowheel otherObj = (Monowheel) obj;
            return maxWeight == otherObj.maxWeight;
        }
        return false;
    }

    /**
     * Returns a copy of the Monowheel object. The copy is made using the
     * Monowheel's class
     * copy constuctor.
     * 
     * @return Monowheel
     */
    @Override
    public Monowheel clone() {
        return new Monowheel(this);
    }

    /**
     * Gets the maxWeight of a Monowheel.
     * 
     * @return double
     */
    public double getMaxWeight() {
        return this.maxWeight;
    }

    /**
     * Sets the maxWeight of a Monowheel.
     * 
     * @param maxWeight
     */
    public void setMaxWeight(double maxWeight) {
        this.maxWeight = maxWeight;
    }
}