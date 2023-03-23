package Exceptions;

public class MissingFieldException extends SyntaxException {
    public MissingFieldException(String field) {
        super("MissingField Exception. Missing: " + field);
    }

    public MissingFieldException(String fileName, String entry, String field) {
        this(field);
        this.fileName = fileName;
        this.entry = entry;
    }
}