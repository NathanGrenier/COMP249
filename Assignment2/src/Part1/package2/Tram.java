// -----------------------------------------------------
// Assignment 2
// Question: Part 1
// Written by: Nathan Grenier, 40250986
// COMP 249
// Due Date: Febuary 24, 2023
// 
// This is the Tram class. It extends Metro.
// -----------------------------------------------------

package Part1.package2;

import Part1.package3.Metro;

/**
 * The Tram class describes Tram objects. It extends the Metro class. It has a
 * new
 * attribute named yearOfCreation.
 * 
 * @author Nathan Grenier
 * @version 1.0
 */
public class Tram extends Metro {
    private int yearOfCreation;

    public Tram() {
        super();
        this.yearOfCreation = 0;
    }

    public Tram(int wheelCount, double maxSpeed, int vehicleCount, String startStation, String endStation,
            int stopCount, int yearOfCreation) {
        super();
        this.yearOfCreation = yearOfCreation;
    }

    public Tram(Tram obj) {
        this(obj.wheelCount, obj.maxSpeed, obj.vehicleCount, obj.startStation, obj.endStation, obj.stopCount,
                obj.yearOfCreation);
    }

    /**
     * Displays the attributes of a Tram object to the console.
     * 
     * @return String
     */
    @Override
    public String toString() {
        return "Tram{" + "yearOfCreation=" + this.yearOfCreation + "} " + super.toString();
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
            Tram otherObj = (Tram) obj;
            return yearOfCreation == otherObj.yearOfCreation;
        }
        return false;
    }

    /**
     * Returns a copy of the Tram object. The copy is made using the Tram's class
     * copy constuctor.
     * 
     * @return Tram
     */
    @Override
    public Tram clone() {
        return new Tram(this);
    }

    /**
     * Gets the yearOfCreation of the Tram object.
     * 
     * @return int
     */
    public int getYearOfCreation() {
        return this.yearOfCreation;
    }

    /**
     * Sets the yearOfCreation of the Tram object.
     * 
     * @param yearOfCreation
     */
    public void setYearOfCreation(int yearOfCreation) {
        this.yearOfCreation = yearOfCreation;
    }
}
