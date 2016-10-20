package by.kanarski.booking.exceptions;

/**
 * An exception that provides information on a l10n operations
 * errors
 * @author Dzmitry Kanarski
 * @version 1.0
 */

public class LocalisationException extends Exception {

    public LocalisationException(String message) {
        super(message);
    }

    public LocalisationException(String message, Throwable cause) {
        super(message, cause);
    }

}
