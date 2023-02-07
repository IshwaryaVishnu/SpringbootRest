package se.lexicon.springbootrest.exception;

public class DataDuplicateException extends RuntimeException{
    public DataDuplicateException(String message) {
        super(message);
    }
}
