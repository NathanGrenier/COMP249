 // -----------------------------------------------------
 // Written by: Nathan Grenier, 40250986
 // COMP249
 // Assignment #3
 // Question: Part 2
 // Due: March 24, 2023
 // -----------------------------------------------------
package Exceptions;

/**
 * BadPriceException
 * 
 * @author Nathan Grenier
 * @version 1.0
 */
public class BadPriceException extends SemanticException {
    public BadPriceException() {
        super("BadPrice Exception.");
    }

    public BadPriceException(String fileName, String entry) {
        this();
        this.fileName = fileName;
        this.entry = entry;
    }
}
