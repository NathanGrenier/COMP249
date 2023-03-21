package Exceptions;

import java.io.PrintWriter;

public class TooManyFieldsException extends Exception {
    private PrintWriter logFile = null;
    private String fileName;
    private String record;
    
    public TooManyFieldsException() {
        super("TooManyFields.");
    }
    
    public TooManyFieldsException(PrintWriter logFile, String fileName, String record) {
        this();
        this.logFile = logFile; 
        this.fileName = fileName;
        this.record = record;
    }

    public void logError() {
        if (this.logFile == null) {
            System.out.println("Can't log error, no output file destination.");
            return;
        }
        this.logFile.println("Syntax Error in File: " + this.fileName);
        this.logFile.println("====================");
        this.logFile.println("Error: " + getMessage());
        this.logFile.println("Record: " + this.record);
    }
}