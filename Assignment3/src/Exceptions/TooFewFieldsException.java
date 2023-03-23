 // -----------------------------------------------------
 // Written by: Nathan Grenier, 40250986
 // COMP249
 // Assignment #3
 // Question: Part 1
 // Due: March 24, 2023
 // -----------------------------------------------------
package Exceptions;

/**
 * TooFewFieldsException
 * 
 * @author Nathan Grenier
 * @version 1.0
 */
public class TooFewFieldsException extends SyntaxException {
    public TooFewFieldsException() {
        super("TooFewFeilds Exception.");
    }

    public TooFewFieldsException(String fileName, String entry) {
        this();
        this.fileName = fileName;
        this.entry = entry;
    }
}