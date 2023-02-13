package Part1.package3;

import javax.print.attribute.standard.MediaSize.Other;

import Part1.package2.Train;

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

    // Getters
    public int getStopCount() {
        return this.stopCount;
    }

    // Setters
    public void setStopCount(int stopCount) {
        this.stopCount = stopCount;
    }
}
