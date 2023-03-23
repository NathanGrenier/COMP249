package Exceptions;

public class BadIsbn10Exception extends SemanticException {
    public BadIsbn10Exception() {
        super("BadIsbn10 Exception.");
    }

    public BadIsbn10Exception(String fileName, String entry) {
        this();
        this.fileName = fileName;
        this.entry = entry;
    }
}
