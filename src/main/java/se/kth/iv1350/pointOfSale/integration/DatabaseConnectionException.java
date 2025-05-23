package se.kth.iv1350.pointOfSale.integration;

/**
 * This is a custom exception class that simulate data connection error and throws an exception.
 */
public class DatabaseConnectionException extends Exception {

    /**
     * Creates a new instance with an error message and a cause.
     * @param errorMessage The message describing the exception.
     * @param cause The cause of the exception.
     */
    public DatabaseConnectionException(String errorMessage, Exception cause) {
        super(errorMessage, cause);
    }

    /**
     * Creates a new instance with an error message.
     * @param errorMessage The message describing the exception.
     */
    public DatabaseConnectionException(String errorMessage) {
        super(errorMessage);
    }
}
