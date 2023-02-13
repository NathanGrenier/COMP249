package Part1.package2;

import Part1.package1.WheeledTransportation;

public class Train extends WheeledTransportation {
    protected int vehicleCount;
    protected String startStation;
    protected String endStation;

    public Train() {
        super();
        this.vehicleCount = 0;
        this.startStation = "undefined";
        this.endStation = "undefined";
    }

    public Train(int wheelCount, double maxSpeed, int vehicleCount, String startStation, String endStation) {
        super(wheelCount, maxSpeed);
        this.vehicleCount = vehicleCount;
        this.startStation = startStation;
        this.endStation = endStation;
    }

    public Train(Train obj) {
        this(obj.wheelCount, obj.maxSpeed, obj.vehicleCount, obj.startStation, obj.endStation);
    }

    @Override
    public String toString() {
        return "Train{" + "vehicleCount=" + this.vehicleCount + ", startStation='" + this.startStation
                + "', endStation='"
                + this.endStation + "'} " + super.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (super.equals(obj)) {
            Train otherObj = (Train) obj;
            return (vehicleCount == otherObj.vehicleCount && startStation.equals(otherObj.startStation)
                    && endStation.equals(endStation));
        }
        return false;
    }

    @Override
    public Train clone() {
        return new Train(this);
    }

    // Getters
    public int getVehicleCount() {
        return this.vehicleCount;
    }

    public String getStartStation() {
        return this.startStation;
    }

    public String getEndStation() {
        return this.endStation;
    }

    // Setters
    public void setVehicleCount(int vehicleCount) {
        this.vehicleCount = vehicleCount;
    }

    public void setStartStation(String startStation) {
        this.startStation = startStation;
    }

    public void setEndStation(String endStation) {
        this.endStation = endStation;
    }
}
