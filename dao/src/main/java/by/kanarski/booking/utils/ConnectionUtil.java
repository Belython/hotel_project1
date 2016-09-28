package by.kanarski.booking.utils;

import by.kanarski.booking.constants.DaoMessages;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

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
            BookingSystemLogger.getInstance().logError(ConnectionUtil.class, DaoMessages.INPUT_ERROR + e);
        } catch (SQLException e) {
            BookingSystemLogger.getInstance().logError(ConnectionUtil.class, DaoMessages.DATABASE_CONNECTION_ERROR + e);
        }
        return connection.get();
    }
}
