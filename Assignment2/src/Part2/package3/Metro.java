// -----------------------------------------------------
// Assignment 2
// Question: Part 2
// Written by: Nathan Grenier, 40250986
// COMP 249
// Due Date: Febuary 24, 2023
// 
// This is the Metro class. It extends the Train class.
// -----------------------------------------------------

package Part2.package3;

import Part2.package2.Train;

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

    @Override
    public String toString() {
        return "Metro{" + "stopCount=" + this.stopCount + "} " + super.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (super.equals(obj)) {
            Metro otherObj = (Metro) obj;
            return stopCount == otherObj.stopCount;
        }
        return false;
    }

    @Override
    public Metro makeCopy() {
        return new Metro(this);
    }

    // Getters
    public int getStopCount() {
        return this.stopCount;
    }

    // Setters
    public void setStopCount(int stopCount) {
        this.stopCount = stopCount;
    }
}
