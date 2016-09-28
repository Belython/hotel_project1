package by.kanarski.booking.utils;

import by.kanarski.booking.constants.DaoMessages;
import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DataSource {
    private static DataSource datasource = null;
    private ComboPooledDataSource cpds = null;

    private DataSource() {
        cpds = new ComboPooledDataSource();
        try {
            String parametersPath = "database";
            String dataBasePath = "dataBasePath";
            String userName = "userName";
            String password = "password";
            String jdbcDriverPath = "jdbcDriverPath";
            ResourceBundle resourceBundle = ResourceBundle.getBundle(parametersPath);
            cpds.setDriverClass(resourceBundle.getString(jdbcDriverPath));
            cpds.setJdbcUrl(resourceBundle.getString(dataBasePath));
            cpds.setUser(resourceBundle.getString(userName));
            cpds.setPassword(resourceBundle.getString(password));
            cpds.setMinPoolSize(5);
            cpds.setAcquireIncrement(5);
            cpds.setMaxPoolSize(20);
            cpds.setMaxStatements(180);
        } catch (PropertyVetoException e) {
            BookingSystemLogger.getInstance().logError(getClass(), DaoMessages.WRONG_DATASOURCE_SETTINGS + e);
        }
    }

    public static synchronized DataSource getInstance() throws IOException, SQLException {
        if (datasource == null) {
            datasource = new DataSource();
        }
        return datasource;
    }

    public Connection getConnection() throws SQLException {
        return this.cpds.getConnection();
    }

}
