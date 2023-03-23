package Exceptions;

public class TooFewFieldsException extends SyntaxException {
    public TooFewFieldsException() {
        super("TooFewFeilds Exception.");
    }

    public TooFewFieldsException(String fileName, String entry) {
        this();
        this.fileName = fileName;
        this.entry = entry;
    }
}