 // -----------------------------------------------------
 // Written by: Nathan Grenier, 40250986
 // COMP249
 // Assignment #3
 // Question: Part 2
 // Due: March 24, 2023
 // -----------------------------------------------------
package Exceptions;

import Main.OutputFile;

/**
 * A class for the SemanticExceptions in part 2. The only differece is in the logError() function.
 * 
 * @author Nathan Grenier
 * @version 1.0
 */
public class SemanticException extends EntryException {
    public SemanticException(String message) {
        super(message);
    }

    /**
     * Logs the SemanticException that was thrown into the OutputFile is was passed.
     * 
     * @param logFile
     */
    public void logError(OutputFile logFile) {
        logFile.outputStream.println("Semantic Error in File: " + this.fileName);
        logFile.outputStream.println("====================");
        logFile.outputStream.println("Error: " + getMessage());
        logFile.outputStream.println("Record: " + this.entry);
        logFile.outputStream.println();
    }
}
