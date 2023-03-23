 // -----------------------------------------------------
 // Written by: Nathan Grenier, 40250986
 // COMP249
 // Assignment #3
 // Question: Part 2
 // Due: March 24, 2023
 // -----------------------------------------------------
package Exceptions;

/**
 * BadIsbn13Exception
 * 
 * @author Nathan Grenier
 * @version 1.0
 */
public class BadIsbn13Exception extends SemanticException {
    public BadIsbn13Exception() {
        super("BadIsbn13 Exception.");
    }

    public BadIsbn13Exception(String fileName, String entry) {
        this();
        this.fileName = fileName;
        this.entry = entry;
    }
}
