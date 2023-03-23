 // -----------------------------------------------------
 // Written by: Nathan Grenier, 40250986
 // COMP249
 // Assignment #3
 // Question: Part 1
 // Due: March 24, 2023
 // -----------------------------------------------------
package Exceptions;

/**
 * TooManyFieldsException
 * 
 * @author Nathan Grenier
 * @version 1.0
 */
public class TooManyFieldsException extends SyntaxException {
    public TooManyFieldsException() {
        super("TooManyFields Exception.");
    }
    
    public TooManyFieldsException(String fileName, String entry) {
        this();
        this.fileName = fileName;
        this.entry = entry;
    }
}