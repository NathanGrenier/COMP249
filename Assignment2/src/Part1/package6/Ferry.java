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

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } else if (this.getClass() != obj.getClass()) {
            return false;
        } else {
            Ferry otherObj = (Ferry) obj;
            return maxSpeed == otherObj.maxSpeed && maxLoad == otherObj.maxLoad;
        }
    }

    @Override
    public Ferry clone() {
        return new Ferry(this);
    }

    // Getters
    public double getMaxSpeed() {
        return maxSpeed;
    }

    public double getMaxLoad() {
        return maxLoad;
    }

    // Setters
    public void setMaxSpeed(double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public void setMaxLoad(double maxLoad) {
        this.maxLoad = maxLoad;
    }
}
