package Exceptions;

public class BadPriceException extends SemanticException {
    public BadPriceException() {
        super("BadPrice Exception.");
    }

    public BadPriceException(String fileName, String record) {
        this();
        this.fileName = fileName;
        this.record = record;
    }
}
