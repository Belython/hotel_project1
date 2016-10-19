package by.kanarski.booking.utils;

import by.kanarski.booking.constants.DaoMessage;
import by.kanarski.booking.utils.dataSource.DataSource;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Dzmitry Kanarski
 * @version 1.0
 */

public class ConnectionUtil {
    private static ThreadLocal<Connection> connection = new ThreadLocal<>();
    private static DataSource instance = null;

    private ConnectionUtil() {
    }

    public static Connection getConnection() {
        try {
            if (instance == null) {
                instance = DataSource.getInstance();
            }
            if (connection.get() == null) {
                connection.set(instance.getConnection());
            }
        } catch (IOException e) {
            BookingSystemLogger.getInstance().logError(ConnectionUtil.class, DaoMessage.INPUT_ERROR + e);
        } catch (SQLException e) {
            BookingSystemLogger.getInstance().logError(ConnectionUtil.class, DaoMessage.DATABASE_CONNECTION_ERROR + e);
        }
        return connection.get();
    }

    public static Connection getTestConnection() {
        try {
            if (instance == null) {
                instance = DataSource.getInstance().test();
            }
            if (connection.get() == null) {
                connection.set(instance.getConnection());
            }
        } catch (IOException e) {
            BookingSystemLogger.getInstance().logError(ConnectionUtil.class, DaoMessage.INPUT_ERROR + e);
        } catch (SQLException e) {
            BookingSystemLogger.getInstance().logError(ConnectionUtil.class, DaoMessage.DATABASE_CONNECTION_ERROR + e);
        }
        return connection.get();
    }
}
