package Exceptions;

public class UnknownGenreException extends SyntaxException {
    public UnknownGenreException() {
        super("UnknownGenre Exception.");
    }

    public UnknownGenreException(String fileName, String entry) {
        this();
        this.fileName = fileName;
        this.entry = entry;
    }
}