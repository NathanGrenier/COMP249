 // -----------------------------------------------------
 // Written by: Nathan Grenier, 40250986
 // COMP249
 // Assignment #3
 // Question: Part 1
 // Due: March 24, 2023
 // -----------------------------------------------------
package Exceptions;

/**
 * MissingFieldException
 * 
 * @author Nathan Grenier
 * @version 1.0
 */
public class MissingFieldException extends SyntaxException {
    public MissingFieldException(String field) {
        super("MissingField Exception. Missing: " + field);
    }

    public MissingFieldException(String fileName, String entry, String field) {
        this(field);
        this.fileName = fileName;
        this.entry = entry;
    }
}