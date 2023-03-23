package Exceptions;

public class TooManyFieldsException extends SyntaxException {
    public TooManyFieldsException() {
        super("TooManyFields Exception.");
    }
    
    public TooManyFieldsException(String fileName, String entry) {
        this();
        this.fileName = fileName;
        this.entry = entry;
    }
}