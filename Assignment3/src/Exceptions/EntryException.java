 // -----------------------------------------------------
 // Written by: Nathan Grenier, 40250986
 // COMP249
 // Assignment #3
 // Question: Part 1, and 2
 // Due: March 24, 2023
 //
 // An abstract class that defines a basic Exception
 // -----------------------------------------------------
package Exceptions;

import Main.OutputFile;

public abstract class EntryException extends Exception {
    protected String fileName;
    protected String entry;
    
    protected EntryException(String message) {
        super(message);
    }
    
    /**
     * Logs the Exception to an error file.
     * 
     * @param logFile
     */
    public abstract void logError(OutputFile logFile);
}