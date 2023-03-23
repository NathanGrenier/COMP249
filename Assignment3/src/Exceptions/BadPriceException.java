package Exceptions;

public class BadPriceException extends SemanticException {
    public BadPriceException() {
        super("BadPrice Exception.");
    }

    public BadPriceException(String fileName, String entry) {
        this();
        this.fileName = fileName;
        this.entry = entry;
    }
}
