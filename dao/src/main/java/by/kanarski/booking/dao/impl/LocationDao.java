package by.kanarski.booking.dao.impl;

import by.kanarski.booking.constants.DaoMessage;
import by.kanarski.booking.dao.interfaces.ILocationDao;
import by.kanarski.booking.entities.Location;
import by.kanarski.booking.exceptions.DaoException;
import by.kanarski.booking.utils.*;
import by.kanarski.booking.utils.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LocationDao implements ILocationDao {

    private static LocationDao instance = null;

    private final String ADD_QUERY = "INSERT INTO LOCATIONS (" +
            "COUNTY, CITY, LOCATION_STATUS)" +
            "VALUES(?, ?, ?);";
    private final String GET_BY_ID_QUERY = "SELECT * FROM LOCATIONS WHERE LOCATION_ID = ?;";
    private final String GET_ALL_QUERY = "SELECT * FROM LOCATIONS;";
    private final String UPDATE_QUERY = "UPDATE LOCATIONS " +
            "SET COUNTRY = ?, CITY = ?, LOCATION_STATUS = ? " +
            "WHERE LOCATION_ID = ?;";
    private final String DELETE_QUERY = "UPDATE ORDERS SET LOCATION_STATUS = 'deleted' WHERE ID = ?;";

    private LocationDao() {
    }

    public static LocationDao getInstance() {
        if (instance == null) {
            instance = new LocationDao();
        }
        return instance;
    }

    @Override
    public Location add(Location location) throws DaoException {
        Connection connection = ConnectionUtil.getConnection();
        ResultSet resultSet = null;
        try (PreparedStatement stm = connection.prepareStatement(ADD_QUERY,
                Statement.RETURN_GENERATED_KEYS)) {
            stm.setString(1, location.getCountry());
            stm.setString(2, location.getCity());
            stm.setString(3, location.getLocationStatus());
            stm.executeUpdate();
            resultSet = stm.getGeneratedKeys();
            resultSet.next();
            location.setLocationId(resultSet.getLong(1));
        } catch (SQLException e) {
            BookingSystemLogger.getInstance().logError(getClass(), DaoMessage.ADD_LOCATION_EXCEPTION);
            throw new DaoException(DaoMessage.ADD_LOCATION_EXCEPTION, e);
        } finally {
            ClosingUtil.close(resultSet);
        }
        return location;
    }

    @Override
    public Location getById(long id) throws DaoException {
        Location location = null;
        Connection connection = ConnectionUtil.getConnection();
        try (PreparedStatement stm = connection.prepareStatement(GET_BY_ID_QUERY)) {
            stm.setLong(1, id);
            ResultSet resultSet = stm.executeQuery();
            resultSet.next();
            location = EntityParser.parseLocation(resultSet);
        } catch (SQLException e) {
            BookingSystemLogger.getInstance().logError(getClass(), DaoMessage.GET_LOCATION_EXCEPTION);
            throw new DaoException(DaoMessage.GET_LOCATION_EXCEPTION, e);
        }
        return location;
    }

    @Override
    public List<Location> getAll() throws DaoException {
        List<Location> locations = new ArrayList<>();
        Connection connection = ConnectionUtil.getConnection();
        try (PreparedStatement stm = connection.prepareStatement(GET_ALL_QUERY)) {
            ResultSet resultSet = stm.executeQuery();
            while (resultSet.next()) {
                locations.add(EntityParser.parseLocation(resultSet));
            }
        } catch (SQLException e) {
            BookingSystemLogger.getInstance().logError(getClass(), DaoMessage.GET_LOCATION_EXCEPTION);
            throw new DaoException(DaoMessage.GET_LOCATION_EXCEPTION, e);
        }
        return locations;
    }

    @Override
    public void update(Location location) throws DaoException {
        Connection connection = ConnectionUtil.getConnection();
        try (PreparedStatement stm = connection.prepareStatement(UPDATE_QUERY)) {
            stm.setString(1, location.getCountry());
            stm.setString(2, location.getCity());
            stm.setString(3, location.getLocationStatus());
            stm.setLong(4, location.getLocationId());
            stm.executeUpdate();
        } catch (SQLException e) {
            BookingSystemLogger.getInstance().logError(getClass(), DaoMessage.UPDATE_LOCATION_EXCEPTION);
            throw new DaoException(DaoMessage.UPDATE_LOCATION_EXCEPTION, e);
        }
    }

    @Override
    public void delete(Location location) throws DaoException {

    }

    public void updateList(List<Location> locationList) throws DaoException {
        Connection connection = ConnectionUtil.getConnection();
        try (PreparedStatement stm = connection.prepareStatement(UPDATE_QUERY)) {
            for (Location location : locationList) {
                stm.setString(1, location.getCountry());
                stm.setString(2, location.getCity());
                stm.setString(3, location.getLocationStatus());
                stm.setLong(4, location.getLocationId());
                stm.addBatch();
            }
            stm.executeBatch();
        } catch (SQLException e) {
            BookingSystemLogger.getInstance().logError(getClass(), DaoMessage.UPDATE_LOCATION_EXCEPTION);
            throw new DaoException(DaoMessage.UPDATE_LOCATION_EXCEPTION, e);
        }
    }

    public void addList(List<Location> locationList) throws DaoException {
        Connection connection = ConnectionUtil.getConnection();
        try (PreparedStatement stm = connection.prepareStatement(ADD_QUERY,
                Statement.RETURN_GENERATED_KEYS)) {
            for (Location location : locationList) {
                stm.setString(1, location.getCountry());
                stm.setString(2, location.getCity());
                stm.setString(3, location.getLocationStatus());
                stm.addBatch();
            }
            stm.executeBatch();
        } catch (SQLException e) {
            BookingSystemLogger.getInstance().logError(getClass(), DaoMessage.ADD_LOCATION_EXCEPTION);
            throw new DaoException(DaoMessage.ADD_LOCATION_EXCEPTION, e);
        }
    }
}
