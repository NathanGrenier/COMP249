package Part1.package4;

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
}