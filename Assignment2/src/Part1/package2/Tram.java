package Part1.package2;

import Part1.package3.Metro;

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
}
