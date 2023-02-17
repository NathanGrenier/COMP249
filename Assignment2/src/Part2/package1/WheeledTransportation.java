// -----------------------------------------------------
// Assignment 2
// Question: Part 2
// Written by: Nathan Grenier, 40250986
// COMP 249
// Due Date: Febuary 24, 2023
// 
// This is the WheeledTransportation class.
// -----------------------------------------------------

package Part2.package1;

import Part2.Copyable;

public class WheeledTransportation implements Copyable {
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

    @Override
    public String toString() {
        return "WheeledTransportation{" + "wheelCount=" + this.wheelCount + ", maxSpeed=" + this.maxSpeed + "} ";
    }

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

    @Override
    public WheeledTransportation makeCopy() {
        return new WheeledTransportation(this);
    }

    // Getters
    public int getWheelCount() {
        return this.wheelCount;
    }

    public double getMaxSpeed() {
        return this.maxSpeed;
    }

    // Setters
    public void setWheelCount(int wheelCount) {
        this.wheelCount = wheelCount;
    }

    public void setMaxSpeed(double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }
}
