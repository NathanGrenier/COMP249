package Exceptions;

import Main.OutputFile;

public class SemanticException extends EntryException {
    public SemanticException(String message) {
        super(message);
    }

    public void logError(OutputFile logFile) {
        logFile.outputStream.println("Semantic Error in File: " + this.fileName);
        logFile.outputStream.println("====================");
        logFile.outputStream.println("Error: " + getMessage());
        logFile.outputStream.println("Record: " + this.entry);
        logFile.outputStream.println();
    }
}
