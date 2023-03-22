package Exceptions;

public class UnknownGenreException extends SyntaxException {
    public UnknownGenreException() {
        super("UnknownGenre Exception.");
    }

    public UnknownGenreException(String fileName, String record) {
        this();
        this.fileName = fileName;
        this.record = record;
    }
}