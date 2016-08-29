package by.kanarski.booking.utils;

import by.kanarski.booking.constants.Messages;
import by.kanarski.booking.exceptions.ServiceException;

import java.sql.Connection;
import java.sql.SQLException;

public class ExceptionHandler {

    public static void getServiceHandler(Connection connection, Exception exception, Class serviceClass) throws ServiceException {
        try {
            connection.rollback();
            BookingSystemLogger.getInstance().logError(serviceClass, Messages.TRANSACTION_FAILED);
            throw new ServiceException(exception.getMessage());
        } catch (SQLException e) {
            BookingSystemLogger.getInstance().logError(serviceClass, Messages.ROLLBACK_FAILED);
            throw new ServiceException(e.getMessage());
        }
    }

}
