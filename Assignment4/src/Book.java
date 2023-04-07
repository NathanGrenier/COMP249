 // -----------------------------------------------------
 // Written by: Nathan Grenier, 40250986
 // COMP249
 // Assignment #4
 // Due: April 17, 2023
 // -----------------------------------------------------

 /**
 * Each book contains:
 * - Title
 * - Author
 * - Price
 * - ISBN
 * - Genre
 * - Year of Creation
 * 
 * @author Nathan Grenier
 * @version 1.0 
 */
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
