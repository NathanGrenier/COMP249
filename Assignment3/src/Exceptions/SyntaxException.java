package Exceptions;

import java.io.PrintWriter;

import Main.OutputFile;

public class SyntaxException extends EntryException{
    public SyntaxException(String message) {
        super(message);
    }

    public void logError(OutputFile logFile) {
        logFile.outputStream.println("Syntax Error in File: " + this.fileName);
        logFile.outputStream.println("====================");
        logFile.outputStream.println("Error: " + getMessage());
        logFile.outputStream.println("Record: " + this.record);
        logFile.outputStream.println();
    }
}
