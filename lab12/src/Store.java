public class Store{
    double sale;
    String storeName;

    public Store() {
        this.sale = 0.0;
        this.storeName = "default";
    }

    public Store(Double sale, String storeName) {
        this.sale = sale;
        this.storeName = storeName;
    }

    public String toString() {
        return "Store{sale=" + this.sale + ", storeName=" + this.storeName + "}";
    }
}
