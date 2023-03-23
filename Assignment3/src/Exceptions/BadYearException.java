package Exceptions;

public class BadYearException extends SemanticException {
    public BadYearException() {
        super("BadYear Exception.");
    }

    public BadYearException(String fileName, String entry) {
        this();
        this.fileName = fileName;
        this.entry = entry;
    }
}
