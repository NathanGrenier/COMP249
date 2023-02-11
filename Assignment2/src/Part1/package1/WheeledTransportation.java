package Part1.package1;

public class WheeledTransportation {
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
}
