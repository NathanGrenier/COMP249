public class Book {
    String title;
    String author;
    double price;
    long ISBN;
    String genre;
    int year;
    
    public Book(String title, String author, double price, long ISBN, String genre, int year) {
        this.title = title;
        this.author = author;
        this.price = price;
        this.ISBN = ISBN;
        this.genre = genre;
        this.year = year;
    }

    public String toString() {
        return this.title + "," + this.author + "," + this.price + "," + this.ISBN + "," + this.genre + "," + this.year;
    }    
}
