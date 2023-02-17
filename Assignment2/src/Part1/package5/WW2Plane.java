// -----------------------------------------------------
// Assignment 2
// Question: Part 1
// Written by: Nathan Grenier, 40250986
// COMP 249
// Due Date: Febuary 24, 2023
// 
// This is the WW2Plane class. It extends Aircraft.
// -----------------------------------------------------

package Part1.package5;

/**
 * The WW2Plane class describes WW2Plane objects. It has a new attributes named
 * twinEngine.
 * 
 * @author Nathan Grenier
 * @version 1.0
 */
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

    /**
     * Displays the attributes of a WW2Plane object to the console.
     * 
     * @return String
     */
    @Override
    public String toString() {
        return "WW2Plane{" + "twinEngine=" + this.twinEngine + "} " + super.toString();
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
            WW2Plane otherObj = (WW2Plane) obj;
            return twinEngine == otherObj.twinEngine;
        }
        return false;
    }

    /**
     * Returns a copy of the WW2Plane object. The copy is made using the WW2Plane's
     * class
     * copy constuctor.
     * 
     * @return WW2Plane
     */
    @Override
    public WW2Plane clone() {
        return new WW2Plane(this);
    }

    /**
     * Gets the value of twinEngine. If true, the WW2Plane has twinEngines.
     * 
     * @return boolean
     */
    public boolean isTwinEngine() {
        return twinEngine;
    }

    /**
     * Sets the value of twinEngine.
     * 
     * @param twinEngine
     */
    public void setTwinEngine(boolean twinEngine) {
        this.twinEngine = twinEngine;
    }

}
