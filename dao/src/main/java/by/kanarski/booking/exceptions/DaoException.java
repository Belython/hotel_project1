package by.kanarski.booking.exceptions;

/**
 * An exception that provides information on a dao operations
 * errors
 * @author Dzmitry Kanarski
 * @version 1.0
 */

public class DaoException extends Exception {

    public DaoException(String message) {
        super(message);
    }

    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }

}
