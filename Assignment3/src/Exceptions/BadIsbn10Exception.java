package Exceptions;

public class BadIsbn10Exception extends SemanticException {
    public BadIsbn10Exception() {
        super("BadIsbn10 Exception.");
    }

    public BadIsbn10Exception(String fileName, String record) {
        this();
        this.fileName = fileName;
        this.record = record;
    }
}
