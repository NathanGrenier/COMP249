package Part2.package5;

import Part2.Copyable;

public class Aircraft implements Copyable {
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

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } else if (this.getClass() == obj.getClass()) {
            return false;
        } else {
            Aircraft otherObj = (Aircraft) obj;
            return price == otherObj.price && maxElavation == otherObj.maxElavation;
        }
    }

    @Override
    public Aircraft makeCopy() {
        return new Aircraft(this);
    }

    // Getters
    public double getPrice() {
        return price;
    }

    public double getMaxElavation() {
        return maxElavation;
    }

    // Setters
    public void setMaxElavation(double maxElavation) {
        this.maxElavation = maxElavation;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
