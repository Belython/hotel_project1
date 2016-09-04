package by.kanarski.booking.utils;

import by.kanarski.booking.constants.ServiceMessages;
import by.kanarski.booking.exceptions.ServiceException;

import java.sql.Connection;
import java.sql.SQLException;

public class ExceptionHandler {

    public static void handleSQLOrDaoException(Connection connection, Exception exception, Class serviceClass) throws ServiceException {
        try {
            connection.rollback();
            BookingSystemLogger.getInstance().logError(serviceClass, ServiceMessages.TRANSACTION_FAILED);
            throw new ServiceException(exception.getMessage());
        } catch (SQLException e) {
            BookingSystemLogger.getInstance().logError(serviceClass, ServiceMessages.ROLLBACK_FAILED);
            throw new ServiceException(e.getMessage());
        }
    }



}
