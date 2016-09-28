package by.kanarski.booking.utils;

import org.apache.log4j.Logger;

public class BookingSystemLogger {
    private static BookingSystemLogger instance;
    private Logger logger;

    private BookingSystemLogger() {
    }


    public static synchronized BookingSystemLogger getInstance() {
        if (instance == null) {
            instance = new BookingSystemLogger();
        }
        return instance;
    }

    public void logError(Class sender, String message) {
        logger = Logger.getLogger(sender);
        logger.error(message);
    }

    public void logError(Class sender, String message, Throwable error) {
        logger = Logger.getLogger(sender);
        logger.error(message, error);
    }

    public void logInfo(Class sender, String message) {
        logger = Logger.getLogger(sender);
        logger.info(message);
    }

}