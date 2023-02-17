// -----------------------------------------------------
// Assignment 2
// Question: Part 1
// Written by: Nathan Grenier, 40250986
// COMP 249
// Due Date: Febuary 24, 2023
// 
// This is the Ferry class.
// -----------------------------------------------------

package Part1.package6;

/**
 * The Ferry class describes Ferry objects. It has new attributes named
 * maxSpeed and maxLoad.
 * 
 * @author Nathan Grenier
 * @version 1.0
 */
public class Ferry {
    private double maxSpeed;
    private double maxLoad;

    public Ferry() {
        this.maxSpeed = 0.0;
        this.maxLoad = 0.0;
    }

    public Ferry(double maxSpeed, double maxLoad) {
        this.maxSpeed = maxSpeed;
        this.maxLoad = maxLoad;
    }

    public Ferry(Ferry obj) {
        this(obj.maxSpeed, obj.maxLoad);
    }

    /**
     * Displays the attributes of a Ferry object to the console.
     * 
     * @return String
     */
    @Override
    public String toString() {
        return "Ferry{" + "maxSpeed=" + this.maxSpeed + ", maxLoad=" + this.maxLoad + "} ";
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
        if (obj == null) {
            return false;
        } else if (this.getClass() != obj.getClass()) {
            return false;
        } else {
            Ferry otherObj = (Ferry) obj;
            return maxSpeed == otherObj.maxSpeed && maxLoad == otherObj.maxLoad;
        }
    }

    /**
     * Returns a copy of the Ferry object. The copy is made using the Ferry's
     * class
     * copy constuctor.
     * 
     * @return Ferry
     */
    @Override
    public Ferry clone() {
        return new Ferry(this);
    }

    /**
     * Gets the Ferry's maxSpeed.
     * 
     * @return double
     */
    public double getMaxSpeed() {
        return maxSpeed;
    }

    /**
     * Gets the Ferry's maxLoad.
     * 
     * @return double
     */
    public double getMaxLoad() {
        return maxLoad;
    }

    /**
     * Sets the Ferry's maxSpeed.
     * 
     * @param maxSpeed
     */
    public void setMaxSpeed(double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    /**
     * Sets the Ferry's maxLoad.
     * 
     * @param maxLoad
     */
    public void setMaxLoad(double maxLoad) {
        this.maxLoad = maxLoad;
    }
}
