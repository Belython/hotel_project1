package by.kanarski.booking.utils;

import by.kanarski.booking.constants.DaoMessage;
import by.kanarski.booking.exceptions.DaoException;
import by.kanarski.booking.utils.dataSource.DataSource;
import by.kanarski.booking.utils.threadLocal.ThreadLocalUtil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Dzmitry Kanarski
 * @version 1.0
 */

public class ConnectionUtil {
    private static Connection connection;
    private static DataSource dataSource = DataSource.getInstance();

    private ConnectionUtil() {
    }

    public static Connection getConnection() {
        try {
            connection = (Connection) ThreadLocalUtil.CONNECTION.get(dataSource.getConnection());
        } catch (SQLException e) {
            BookingSystemLogger.getInstance().logError(ConnectionUtil.class, DaoMessage.INPUT_ERROR, e);
        }
        return connection;
    }

}
