 // -----------------------------------------------------
 // Written by: Nathan Grenier, 40250986
 // COMP249
 // Assignment #3
 // Question: Part 3
 // Due: March 24, 2023
 // -----------------------------------------------------
package Main;

import java.io.Serializable;

/**
 * A Book contains the information of a book record and is serializable.
 * 
 * @author Nathan Grenier
 * @version 1.0
 */
public class Book implements Serializable {
    private String title;
    private String authors;
    private double price;
    private String isbn;
    private int year;

    public Book(String title, String authors, double price, String isbn, int year) {
        this.title = title;
        this.authors = authors;
        this.price = price;
        this.isbn = isbn;
        this.year = year;
    }

    /**
     * Verifies if 2 objects are of the same type and that their attributes are all
     * equal.
     * 
     * @param otherObj
     * @return boolean
     */
    @Override
    public boolean equals(Object otherObj) {
        if (otherObj == null) {
            return false;
        } else if (this.getClass() != otherObj.getClass()) {
            return false;
        } else {
            Book obj = (Book) otherObj;
            return this.title == obj.title && this.authors == obj.authors && this.price == obj.price && this.isbn == obj.isbn && this.year == obj.year;
        }
    }

    /**
     * Returns the attributes of a Book object.
     * 
     * @return String
     */
    @Override
    public String toString() {
        return "Book{title=" + this.title + ", authors=" + this.authors + ", price=" + this.price + ", isbn=" + this.isbn + ", year=" + this.year + "}";
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
