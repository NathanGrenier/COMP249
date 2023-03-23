 // -----------------------------------------------------
 // Written by: Nathan Grenier, 40250986
 // COMP249
 // Assignment #3
 // Question: Part 2
 // Due: March 24, 2023
 // -----------------------------------------------------
package Exceptions;

/**
 * BadIsbn10Exception
 * 
 * @author Nathan Grenier
 * @version 1.0
 */
public class BadIsbn10Exception extends SemanticException {
    public BadIsbn10Exception() {
        super("BadIsbn10 Exception.");
    }

    public BadIsbn10Exception(String fileName, String entry) {
        this();
        this.fileName = fileName;
        this.entry = entry;
    }
}
