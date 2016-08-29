package by.kanarski.booking.utils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClosingUtil {

    private ClosingUtil() {
    }

    public static void close(PreparedStatement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                BookingSystemLogger.getInstance().logError(ClosingUtil.class, e.getMessage());
            }
        }
    }

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
