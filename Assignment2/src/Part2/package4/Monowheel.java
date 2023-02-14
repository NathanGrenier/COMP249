package Part2.package4;

import Part1.package1.WheeledTransportation;

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

    @Override
    public String toString() {
        return "Monowheel{" + "maxWeight=" + this.maxWeight + "} " + super.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (super.equals(obj)) {
            Monowheel otherObj = (Monowheel) obj;
            return maxWeight == otherObj.maxWeight;
        }
        return false;
    }

    public Monowheel makeCopy() {
        return new Monowheel(this);
    }

    // Getters
    public double getMaxWeight() {
        return this.maxWeight;
    }

    // Setters
    public void setMaxWeight(double maxWeight) {
        this.maxWeight = maxWeight;
    }
}