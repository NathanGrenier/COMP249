package Exceptions;

import Main.OutputFile;

public abstract class EntryException extends Exception {
    protected String fileName;
    protected String entry;
    
    protected EntryException(String message) {
        super(message);
    }
    
    public abstract void logError(OutputFile logFile);
}