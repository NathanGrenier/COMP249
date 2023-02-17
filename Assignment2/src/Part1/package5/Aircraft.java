// -----------------------------------------------------
// Assignment 2
// Question: Part 1
// Written by: Nathan Grenier, 40250986
// COMP 249
// Due Date: Febuary 24, 2023
// 
// This is the Aircraft class.
// -----------------------------------------------------

package Part1.package5;

/**
 * The Aircraft class describes Aircraft objects. It has new attributes named
 * price and maxElevation.
 * 
 * @author Nathan Grenier
 * @version 1.0
 */
public class Aircraft {
    protected double price;
    protected double maxElavation;

    public Aircraft() {
        this.price = 0.0;
        this.maxElavation = 0.0;
    }

    public Aircraft(double price, double maxElavation) {
        this.price = price;
        this.maxElavation = maxElavation;
    }

    public Aircraft(Aircraft obj) {
        this(obj.price, obj.maxElavation);
    }

    /**
     * Displays the attributes of a Aircraft object to the console.
     * 
     * @return String
     */
    @Override
    public String toString() {
        return "Aircraft{" + "price=" + this.price + ", maxElevation=" + this.maxElavation + "} ";
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
        } else if (this.getClass() == obj.getClass()) {
            return false;
        } else {
            Aircraft otherObj = (Aircraft) obj;
            return price == otherObj.price && maxElavation == otherObj.maxElavation;
        }
    }

    /**
     * Returns a copy of the Aircraft object. The copy is made using the Aircraft's
     * class
     * copy constuctor.
     * 
     * @return Aircraft
     */
    @Override
    public Aircraft clone() {
        return new Aircraft(this);
    }

    /**
     * Gets the price of an Aircraft object.
     * 
     * @return double
     */
    public double getPrice() {
        return price;
    }

    /**
     * Gets the maxElevation of an Aircraft object.
     * 
     * @return int
     */
    public double getMaxElavation() {
        return maxElavation;
    }

    /**
     * Sets the maxElevation of an Aircraft object.
     * 
     * @param maxElavation
     */
    public void setMaxElavation(double maxElavation) {
        this.maxElavation = maxElavation;
    }

    /**
     * Sets the price of an Aircraft object.
     * 
     * @param price
     */
    public void setPrice(double price) {
        this.price = price;
    }

}
