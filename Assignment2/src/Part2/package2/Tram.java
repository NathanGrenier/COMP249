// -----------------------------------------------------
// Assignment 2
// Question: Part 2
// Written by: Nathan Grenier, 40250986
// COMP 249
// Due Date: Febuary 24, 2023
// 
// This is the Tram class. It extends the Metro class.
// -----------------------------------------------------

package Part2.package2;

import Part2.package3.Metro;

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

    @Override
    public String toString() {
        return "Tram{" + "yearOfCreation=" + this.yearOfCreation + "} " + super.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (super.equals(obj)) {
            Tram otherObj = (Tram) obj;
            return yearOfCreation == otherObj.yearOfCreation;
        }
        return false;
    }

    @Override
    public Tram makeCopy() {
        return new Tram(this);
    }

    // Getters
    public int getYearOfCreation() {
        return this.yearOfCreation;
    }

    // Setters
    public void setYearOfCreation(int yearOfCreation) {
        this.yearOfCreation = yearOfCreation;
    }
}
