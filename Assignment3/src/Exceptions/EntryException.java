package Exceptions;

import java.io.PrintWriter;

public abstract class EntryException extends Exception {
    protected String fileName;
    protected String record;
    
    public EntryException(String message) {
        super(message);
    }
    
    public abstract void logError(PrintWriter logFile);
}