// -----------------------------------------------------
// Assignment 2
// Question: Part 2
// Written by: Nathan Grenier, 40250986
// COMP 249
// Due Date: Febuary 24, 2023
// 
// This is the WW2Plane class. It extends the Aircraft class.
// -----------------------------------------------------

package Part2.package5;

public class WW2Plane extends Aircraft {
    private boolean twinEngine;

    public WW2Plane() {
        super();
        this.twinEngine = false;
    }

    public WW2Plane(double price, double maxElavation, boolean twinEngine) {
        super(price, maxElavation);
        this.twinEngine = twinEngine;
    }

    public WW2Plane(WW2Plane obj) {
        this(obj.price, obj.maxElavation, obj.twinEngine);
    }

    @Override
    public String toString() {
        return "WW2Plane{" + "twinEngine=" + this.twinEngine + "} " + super.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (super.equals(obj)) {
            WW2Plane otherObj = (WW2Plane) obj;
            return twinEngine == otherObj.twinEngine;
        }
        return false;
    }

    @Override
    public WW2Plane makeCopy() {
        return new WW2Plane(this);
    }

    // Getters
    public boolean isTwinEngine() {
        return twinEngine;
    }

    // Setters
    public void setTwinEngine(boolean twinEngine) {
        this.twinEngine = twinEngine;
    }

}
