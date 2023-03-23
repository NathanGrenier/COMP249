 // -----------------------------------------------------
 // Written by: Nathan Grenier, 40250986
 // COMP249
 // Assignment #3
 // Question: Part 1
 // Due: March 24, 2023
 // -----------------------------------------------------
package Exceptions;

import Main.OutputFile;

/**
 * A class for the SyntaxExceptions in part 1. The only differece is in the logError() function.
 * 
 * @author Nathan Grenier
 * @version 1.0
 */
public class SyntaxException extends EntryException{
    public SyntaxException(String message) {
        super(message);
    }

    /**
     * Logs the SyntaxException that was thrown into the OutputFile is was passed.
     * 
     * @param logFile
     */
    public void logError(OutputFile logFile) {
        logFile.outputStream.println("Syntax Error in File: " + this.fileName);
        logFile.outputStream.println("====================");
        logFile.outputStream.println("Error: " + getMessage());
        logFile.outputStream.println("Record: " + this.entry);
        logFile.outputStream.println();
    }
}
