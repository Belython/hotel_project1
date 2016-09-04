package by.kanarski.booking.dao.impl;

import by.kanarski.booking.constants.DaoMessages;
import by.kanarski.booking.dao.interfaces.IHotelDao;
import by.kanarski.booking.entities.Hotel;
import by.kanarski.booking.exceptions.DaoException;
import by.kanarski.booking.utils.BookingSystemLogger;
import by.kanarski.booking.utils.ClosingUtil;
import by.kanarski.booking.utils.ConnectionUtil;
import by.kanarski.booking.utils.EntityParser;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HotelDao implements IHotelDao {

    private static HotelDao instance = null;
    private final String ADD_QUERY = "INSERT INTO HOTELS (LOCATION_ID, HOTEL_NAME) VALUES(" +
            "(SELECT LOCATION_ID FROM LOCATIONS WHERE COUNTRY = ? AND CITY = ?), ?)";
    private final String GET_BY_ID_QUERY = "SELECT H.*, L.* " +
            "FROM HOTELS H " +
            "JOIN LOCATIONS L ON H.LOCATION_ID = L.LOCATION_ID WHERE H.HOTEL_ID = ?";
    private final String GET_ALL_QUERY = "SELECT H.*, L.* " +
            "FROM HOTELS H " +
            "JOIN LOCATIONS L ON H.LOCATION_ID = L.LOCATION_ID";
    private final String GET_BY_HOTEL_NAME_QUERY = "SELECT H.*, L.* " +
            "FROM HOTELS H " +
            "JOIN LOCATIONS L ON H.LOCATION_ID = L.LOCATION_ID WHERE H.HOTEL_NAME = ?";

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
            stm.setString(1, hotel.getCountry());
            stm.setString(2, hotel.getCity());
            stm.setString(3, hotel.getName());
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
}
