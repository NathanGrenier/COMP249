package Exceptions;

public class TooFewFieldsException extends SyntaxException {
    public TooFewFieldsException() {
        super("TooFewFeilds Exception.");
    }

    public TooFewFieldsException(String fileName, String record) {
        this();
        this.fileName = fileName;
        this.record = record;
    }
}