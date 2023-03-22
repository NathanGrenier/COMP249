package Exceptions;

public class BadYearException extends SemanticException {
    public BadYearException() {
        super("BadYear Exception.");
    }

    public BadYearException(String fileName, String record) {
        this();
        this.fileName = fileName;
        this.record = record;
    }
}
