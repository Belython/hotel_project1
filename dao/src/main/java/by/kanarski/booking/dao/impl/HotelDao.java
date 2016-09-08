package by.kanarski.booking.dao.impl;

import by.kanarski.booking.constants.DaoMessages;
import by.kanarski.booking.dao.interfaces.IHotelDao;
import by.kanarski.booking.entities.Hotel;
import by.kanarski.booking.entities.Room;
import by.kanarski.booking.exceptions.DaoException;
import by.kanarski.booking.utils.BookingSystemLogger;
import by.kanarski.booking.utils.ClosingUtil;
import by.kanarski.booking.utils.ConnectionUtil;
import by.kanarski.booking.utils.EntityParser;

import java.lang.reflect.Parameter;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HotelDao implements IHotelDao {

    private static HotelDao instance = null;
    private final String ADD_QUERY = "INSERT INTO HOTELS (LOCATION_ID, HOTEL_NAME, HOTEL_STATUS) VALUES(" +
            "(SELECT LOCATION_ID FROM LOCATIONS WHERE COUNTRY = ? AND CITY = ?), ?, ?)";
    private final String GET_BY_ID_QUERY = "SELECT H.*, L.* " +
            "FROM HOTELS H " +
            "JOIN LOCATIONS L ON H.LOCATION_ID = L.LOCATION_ID WHERE H.HOTEL_ID = ?";
    private final String GET_ALL_QUERY = "SELECT H.*, L.* " +
            "FROM HOTELS H " +
            "JOIN LOCATIONS L ON H.LOCATION_ID = L.LOCATION_ID";
    private final String GET_BY_HOTEL_NAME_QUERY = "SELECT H.*, L.* " +
            "FROM HOTELS H " +
            "JOIN LOCATIONS L ON H.LOCATION_ID = L.LOCATION_ID WHERE H.HOTEL_NAME = ?";
    private final String UPDATE_HOTEL_QUERY = "UPDATE HOTELS " +
            "SET HOTEL_NAME = ?, HOTEL_STATUS = ? WHERE HOTEL_ID = ?";
    private final String GET_BY_COUNTRY_QUERY = "SELECT H.*, L.* " +
            "FROM HOTELS H " +
            "JOIN LOCATIONS L ON H.LOCATION_ID = L.LOCATION_ID WHERE L.COUNTRY = ?";
    private final String GET_BY_CITY_QUERY = "SELECT H.*, L.* " +
            "FROM HOTELS H " +
            "JOIN LOCATIONS L ON H.LOCATION_ID = L.LOCATION_ID WHERE L.CITY = ?";
    private final String GET_FIELDS_VALUES = "SELECT H.HOTEL_NAME, L.COUNTRY, L.CITY " +
            "FROM HOTELS H " +
            "JOIN LOCATIONS L ON H.LOCATION_ID = L.LOCATION_ID";

    private HotelDao() {
    }

    public static HotelDao getInstance() {
        if (instance == null) {
            instance = new HotelDao();
        }
        return instance;
    }

    @Override
    public Hotel add(Hotel hotel) throws DaoException {
        Connection connection = ConnectionUtil.getConnection();
        ResultSet resultSet = null;
        try (PreparedStatement stm = connection.prepareStatement(ADD_QUERY,
                Statement.RETURN_GENERATED_KEYS)) {
            stm.setString(1, hotel.getLocation().getCountry());
            stm.setString(2, hotel.getLocation().getCity());
            stm.setString(3, hotel.getName());
            stm.setString(4, hotel.getStatus());
            stm.executeUpdate();
            resultSet = stm.getGeneratedKeys();
            resultSet.next();
            hotel.setId(resultSet.getLong(1));
        } catch (SQLException e) {
            BookingSystemLogger.getInstance().logError(getClass(), DaoMessages.ADD_HOTEL_EXCEPTION);
            throw new DaoException(DaoMessages.ADD_HOTEL_EXCEPTION, e);
        } finally {
            ClosingUtil.close(resultSet);
        }
        return hotel;
    }

    @Override
    public Hotel getById(long id) throws DaoException {
        Hotel hotel = null;
        Connection connection = ConnectionUtil.getConnection();
        ResultSet resultSet = null;
        try (PreparedStatement stm = connection.prepareStatement(GET_BY_ID_QUERY)) {
            stm.setLong(1, id);
            resultSet = stm.executeQuery();
            resultSet.next();
            hotel = EntityParser.parseHotel(resultSet);
        } catch (SQLException e) {
            BookingSystemLogger.getInstance().logError(getClass(), DaoMessages.GET_HOTEL_EXCEPTION);
            throw new DaoException(DaoMessages.GET_HOTEL_EXCEPTION, e);
        } finally {
            ClosingUtil.close(resultSet);
        }
        return hotel;
    }

    @Override
    public void update(Hotel hotel) throws DaoException {

    }

    @Override
    public void delete(Hotel hotel) throws DaoException {

    }

    public void updateList(List<Hotel> hotelList) throws DaoException{
        Connection connection = ConnectionUtil.getConnection();
        try (PreparedStatement stm = connection.prepareStatement(UPDATE_HOTEL_QUERY)) {
            for (Hotel hotel : hotelList) {
                stm.setString(1, hotel.getName());
                stm.setString(2, hotel.getStatus());
                stm.setLong(3, hotel.getId());
                stm.addBatch();
            }
            stm.executeBatch();
        } catch (SQLException e) {
            BookingSystemLogger.getInstance().logError(getClass(), DaoMessages.UPDATE_HOTEL_EXCEPTION);
            throw new DaoException(DaoMessages.UPDATE_HOTEL_EXCEPTION, e);
        }
    }

    @Override
    public List<Hotel> getAll() throws DaoException{
        List<Hotel> hotelList = new ArrayList<>();
        Connection connection = ConnectionUtil.getConnection();
        ResultSet resultSet = null;
        try (PreparedStatement stm = connection.prepareStatement(GET_ALL_QUERY)) {
            resultSet = stm.executeQuery();
            while (resultSet.next()) {
                hotelList.add(EntityParser.parseHotel(resultSet));
            }
        } catch (SQLException e) {
            BookingSystemLogger.getInstance().logError(getClass(), DaoMessages.GET_HOTEL_EXCEPTION);
            throw new DaoException(DaoMessages.GET_HOTEL_EXCEPTION, e);
        } finally {
            ClosingUtil.close(resultSet);
        }
        return hotelList;
    }

    public Hotel getByHotelName(String hotelName) throws DaoException {
        Hotel hotel = null;
        Connection connection = ConnectionUtil.getConnection();
        ResultSet resultSet = null;
        try (PreparedStatement stm = connection.prepareStatement(GET_BY_HOTEL_NAME_QUERY)) {
            stm.setString(1, hotelName);
            resultSet = stm.executeQuery();
            resultSet.next();
            hotel = EntityParser.parseHotel(resultSet);
        } catch (SQLException e) {
            BookingSystemLogger.getInstance().logError(getClass(), DaoMessages.GET_HOTEL_EXCEPTION);
            throw new DaoException(DaoMessages.GET_HOTEL_EXCEPTION, e);
        } finally {
            ClosingUtil.close(resultSet);
        }
        return hotel;
    }

    public List<Hotel> getByCountry(String country) throws DaoException {
        List<Hotel> hotelList = new ArrayList<>();
        Connection connection = ConnectionUtil.getConnection();
        ResultSet resultSet = null;
        try (PreparedStatement stm = connection.prepareStatement(GET_BY_COUNTRY_QUERY)) {
            stm.setString(1, country);
            resultSet = stm.executeQuery();
            while (resultSet.next()) {
                hotelList.add(EntityParser.parseHotel(resultSet));
            }
        } catch (SQLException e) {
            BookingSystemLogger.getInstance().logError(getClass(), DaoMessages.GET_HOTEL_EXCEPTION);
            throw new DaoException(DaoMessages.GET_HOTEL_EXCEPTION, e);
        } finally {
            ClosingUtil.close(resultSet);
        }
        return hotelList;
    }

    public List<Hotel> getByCity(String city) throws DaoException {
        List<Hotel> hotelList = new ArrayList<>();
        Connection connection = ConnectionUtil.getConnection();
        ResultSet resultSet = null;
        try (PreparedStatement stm = connection.prepareStatement(GET_BY_CITY_QUERY)) {
            stm.setString(1, city);
            resultSet = stm.executeQuery();
            while (resultSet.next()) {
                hotelList.add(EntityParser.parseHotel(resultSet));
            }
        } catch (SQLException e) {
            BookingSystemLogger.getInstance().logError(getClass(), DaoMessages.GET_HOTEL_EXCEPTION);
            throw new DaoException(DaoMessages.GET_HOTEL_EXCEPTION, e);
        } finally {
            ClosingUtil.close(resultSet);
        }
        return hotelList;
    }

    public void addList(List<Hotel> hotelList) throws DaoException {
        Connection connection = ConnectionUtil.getConnection();
        try (PreparedStatement stm = connection.prepareStatement(ADD_QUERY,
                Statement.RETURN_GENERATED_KEYS)) {
            for (Hotel hotel: hotelList) {
                stm.setString(1, hotel.getLocation().getCountry());
                stm.setString(2, hotel.getLocation().getCity());
                stm.setString(3, hotel.getName());
                stm.setString(4, hotel.getStatus());
                stm.addBatch();
            }
            stm.executeBatch();
        } catch (SQLException e) {
            BookingSystemLogger.getInstance().logError(getClass(), DaoMessages.ADD_HOTEL_EXCEPTION);
            throw new DaoException(DaoMessages.ADD_HOTEL_EXCEPTION, e);
        }
    }

    public HashMap<String, List<String>> getFieldsValues() throws DaoException {
        HashMap<String, List<String>> fieldsValuesMap = new HashMap<>();
        Connection connection = ConnectionUtil.getConnection();
        ResultSet resultSet = null;
        try (PreparedStatement stm = connection.prepareStatement(GET_ALL_QUERY)) {
            resultSet = stm.executeQuery();
            while (resultSet.next()) {
                fieldsValuesMap.put()
                hotelList.add(EntityParser.parseHotel(resultSet));
            }
        } catch (SQLException e) {
            BookingSystemLogger.getInstance().logError(getClass(), DaoMessages.GET_HOTEL_EXCEPTION);
            throw new DaoException(DaoMessages.GET_HOTEL_EXCEPTION, e);
        } finally {
            ClosingUtil.close(resultSet);
        }
        return hotelList;
    }
}
