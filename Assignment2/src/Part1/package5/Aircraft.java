package Part1.package5;

public class Aircraft {
    protected double price;
    protected double maxElavation;

    public Aircraft() {
        this.price = 0.0;
        this.maxElavation = 0.0;
    }

    public Aircraft(double price, double maxElavation) {
        this.price = price;
        this.maxElavation = maxElavation;
    }

    public Aircraft(Aircraft obj) {
        this(obj.price, obj.maxElavation);
    }

    @Override
    public String toString() {
        return "Aircraft{" + "price=" + this.price + ", maxElevation=" + this.maxElavation + "} ";
    }
}
