package Part1.package6;

public class Ferry {
    private double maxSpeed;
    private double maxLoad;

    public Ferry() {
        this.maxSpeed = 0.0;
        this.maxLoad = 0.0;
    }

    public Ferry(double maxSpeed, double maxLoad) {
        this.maxSpeed = maxSpeed;
        this.maxLoad = maxLoad;
    }

    public Ferry(Ferry obj) {
        this(obj.maxSpeed, obj.maxLoad);
    }

    @Override
    public String toString() {
        return "Ferry{" + "maxSpeed=" + this.maxSpeed + ", maxLoad=" + this.maxLoad + "} ";
    }
}
