// -----------------------------------------------------
// Assignment 2
// Question: Part 1
// Written by: Nathan Grenier, 40250986
// COMP 249
// Due Date: Febuary 24, 2023
// 
// This is the Wheeled Transportation class
// -----------------------------------------------------

package Part1.package1;

/**
 * <p>
 * The WheeledTransportation class describes vehicles whose method of
 * transportation is with wheels.
 * </p>
 * 
 * @author Nathan Grenier
 * @version 1.0
 * 
 */
public class WheeledTransportation {
    protected int wheelCount;
    protected double maxSpeed;

    public WheeledTransportation() {
        this.wheelCount = 0;
        this.maxSpeed = 0.0;
    }

    public WheeledTransportation(int wheelCount, double maxSpeed) {
        this.wheelCount = wheelCount;
        this.maxSpeed = maxSpeed;
    }

    public WheeledTransportation(WheeledTransportation obj) {
        this(obj.wheelCount, obj.maxSpeed);
    }

    /**
     * Returns a String of the WheeledTransportation's fields.
     * 
     * @return String
     */
    @Override
    public String toString() {
        return "WheeledTransportation{" + "wheelCount=" + this.wheelCount + ", maxSpeed=" + this.maxSpeed + "} ";
    }

    /**
     * Evaluates if 2 objectes are equal in type and attributes.
     * 
     * @param obj
     * @return boolean
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } else if (this.getClass() != obj.getClass()) {
            return false;
        } else {
            WheeledTransportation otherObj = (WheeledTransportation) obj;
            return (wheelCount == otherObj.wheelCount && maxSpeed == otherObj.maxSpeed);
        }
    }

    /**
     * Clones the instance of WheeledTransportation that the method was called on.
     * The copy is performed by the copy constructor.
     * 
     * @return WheeledTransportation
     */
    @Override
    public WheeledTransportation clone() {
        return new WheeledTransportation(this);
    }

    /**
     * Returns the number of wheels of the WheeledTransportation Object.
     * 
     * @return int
     */
    public int getWheelCount() {
        return this.wheelCount;
    }

    /**
     * Gets the max speed of the WheeledTransportation Object.
     * 
     * @return double
     */
    public double getMaxSpeed() {
        return this.maxSpeed;
    }

    /**
     * Sets the number of wheels of the WheeledTransportation Object.
     * 
     * @param wheelCount
     */
    public void setWheelCount(int wheelCount) {
        this.wheelCount = wheelCount;
    }

    /**
     * Sets the max speed of the WheeledTransportation Object.
     * 
     * @param maxSpeed
     */
    public void setMaxSpeed(double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }
}
