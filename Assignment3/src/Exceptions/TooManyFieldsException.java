package Exceptions;

public class TooManyFieldsException extends SyntaxException {
    public TooManyFieldsException() {
        super("TooManyFields Exception.");
    }
    
    public TooManyFieldsException(String fileName, String record) {
        this();
        this.fileName = fileName;
        this.record = record;
    }
}