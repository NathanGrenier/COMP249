 // -----------------------------------------------------
 // Written by: Nathan Grenier, 40250986
 // COMP249
 // Assignment #3
 // Question: Part 1
 // Due: March 24, 2023
 // -----------------------------------------------------
package Exceptions;

/**
 * UnknownGenreException
 * 
 * @author Nathan Grenier
 * @version 1.0
 */
public class UnknownGenreException extends SyntaxException {
    public UnknownGenreException() {
        super("UnknownGenre Exception.");
    }

    public UnknownGenreException(String fileName, String entry) {
        this();
        this.fileName = fileName;
        this.entry = entry;
    }
}