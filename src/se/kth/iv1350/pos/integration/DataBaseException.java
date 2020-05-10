package se.kth.iv1350.pos.integration;

/**
 * This exception is thrown when the data base can't be reached.
 */
public class DataBaseException extends Exception {

    public DataBaseException(String message) {
        super(message);
    }
}
