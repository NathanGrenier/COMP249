package Exceptions;

import java.io.PrintWriter;

public class SyntaxException extends EntryException{
    public SyntaxException(String message) {
        super(message);
    }

    public void logError(PrintWriter logFile) {
        logFile.println("Syntax Error in File: " + this.fileName);
        logFile.println("====================");
        logFile.println("Error: " + getMessage());
        logFile.println("Record: " + this.record);
        logFile.println();
    }
}
