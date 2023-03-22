package Exceptions;

public class BadIsbn13Exception extends SemanticException {
    public BadIsbn13Exception() {
        super("BadIsbn13 Exception.");
    }

    public BadIsbn13Exception(String fileName, String record) {
        this();
        this.fileName = fileName;
        this.record = record;
    }
}
