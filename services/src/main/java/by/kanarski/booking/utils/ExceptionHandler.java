package by.kanarski.booking.utils;

import by.kanarski.booking.constants.ServiceMessage;
import by.kanarski.booking.exceptions.ServiceException;

import java.sql.Connection;
import java.sql.SQLException;

public class ExceptionHandler {

    public static void handleSQLOrDaoException(Connection connection, Exception exception, Class serviceClass) throws ServiceException {
        try {
            connection.rollback();
//            BookingSystemLogger.getInstance().logError(serviceClass, ServiceMessage.TRANSACTION_FAILED);
            throw new ServiceException(exception.getMessage(), exception);
        } catch (SQLException e) {
//            BookingSystemLogger.getInstance().logError(serviceClass, ServiceMessage.ROLLBACK_FAILED);
            throw new ServiceException(e.getMessage(), exception);
        }
    }



}
