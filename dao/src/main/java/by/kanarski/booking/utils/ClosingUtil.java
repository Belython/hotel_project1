package by.kanarski.booking.utils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Provides methods for safe closing <code>AutoCloseable</code> objects
 * @author Dzmitry Kanarski
 * @version 1.0
 * @see {@link AutoCloseable}
 */

public class ClosingUtil {

    private ClosingUtil() {
    }

    /**
     * Closes <code>PreparedStatement</code>
     * @param statement closing <code>PreparedStatement</code>
     */

    public static void close(PreparedStatement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                BookingSystemLogger.getInstance().logError(ClosingUtil.class, e.getMessage());
            }
        }
    }

    /**
     * Closes <code>ResultSet</code>
     * @param resultSet closing <code>ResulSet</code>
     */

    public static void close(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                BookingSystemLogger.getInstance().logError(ClosingUtil.class, e.getMessage());
            }
        }
    }
}
