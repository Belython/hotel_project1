package by.kanarski.booking.utils;

import by.kanarski.booking.constants.DaoMessage;
import by.kanarski.booking.utils.threadLocal.ThreadLocalUtil;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Dzmitry Kanarski
 * @version 1.0
 */

public class ConnectionUtil {
    private static DataSource dataSource = DataSource.getInstance();

    private ConnectionUtil() {
    }

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Connection newConnection = dataSource.getConnection();
            connection = (Connection) ThreadLocalUtil.CONNECTION.get(newConnection);
            if (!connection.equals(newConnection)) {
                newConnection.close();
            }
        } catch (SQLException e) {
            BookingSystemLogger.getInstance().logError(ConnectionUtil.class, DaoMessage.INPUT_ERROR, e);
        }
        return connection;
    }

}
