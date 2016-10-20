package by.kanarski.booking.services.impl;

import by.kanarski.booking.constants.ServiceMessage;
import by.kanarski.booking.dao.impl.HotelDao;
import by.kanarski.booking.entities.Hotel;
import by.kanarski.booking.exceptions.LocalisationException;
import by.kanarski.booking.exceptions.ServiceException;
import by.kanarski.booking.services.interfaces.IHotelService;
import by.kanarski.booking.utils.BookingSystemLogger;
import by.kanarski.booking.utils.ConnectionUtil;
import by.kanarski.booking.utils.ExceptionHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;


public class HotelServiceImpl implements IHotelService {

    private static HotelServiceImpl instance;

    private HotelServiceImpl() {
    }

    public static synchronized HotelServiceImpl getInstance() {
        if (instance == null) {
            instance = new HotelServiceImpl();
        }
        return instance;
    }

    @Override
    public void add(Hotel entity) throws ServiceException {

    }

    @Override
    public List<Hotel> getAll() throws ServiceException {
        Connection connection = ConnectionUtil.getConnection();
        List<Hotel> hotelList = null;
        try {
            connection.setAutoCommit(false);
            hotelList = HotelDao.getInstance().getAll();
            connection.commit();
            BookingSystemLogger.getInstance().logInfo(getClass(), ServiceMessage.TRANSACTION_SUCCEEDED);
        } catch (SQLException | LocalisationException e) {
            ExceptionHandler.handleSQLOrDaoException(connection, e, getClass());
        }
        return hotelList;
    }

    @Override
    public Hotel getById(long id) throws ServiceException {
        Connection connection = ConnectionUtil.getConnection();
        Hotel hotel = null;
        try {
            connection.setAutoCommit(false);
            hotel = HotelDao.getInstance().getById(id);
            connection.commit();
            BookingSystemLogger.getInstance().logInfo(getClass(), ServiceMessage.TRANSACTION_SUCCEEDED);
        } catch (SQLException | LocalisationException e) {
            ExceptionHandler.handleSQLOrDaoException(connection, e, getClass());
        }
        return hotel;
    }

    @Override
    public void update(Hotel entity) throws ServiceException {

    }

    @Override
    public void delete(long id) throws ServiceException {

    }

    public Hotel getByHotelName(String hotelName) throws ServiceException {
        Connection connection = ConnectionUtil.getConnection();
        Hotel hotel = null;
        try {
            connection.setAutoCommit(false);
            hotel = HotelDao.getInstance().getByHotelName(hotelName);
            connection.commit();
            BookingSystemLogger.getInstance().logInfo(getClass(), ServiceMessage.TRANSACTION_SUCCEEDED);
        } catch (SQLException | LocalisationException e) {
            ExceptionHandler.handleSQLOrDaoException(connection, e, getClass());
        }
        return hotel;
    }

    public void updateList(List<Hotel> hotelList) throws ServiceException {
        Connection connection = ConnectionUtil.getConnection();
        try {
            connection.setAutoCommit(false);
            HotelDao.getInstance().updateList(hotelList);
            connection.commit();
            BookingSystemLogger.getInstance().logInfo(getClass(), ServiceMessage.TRANSACTION_SUCCEEDED);
        } catch (SQLException | LocalisationException e) {
            ExceptionHandler.handleSQLOrDaoException(connection, e, getClass());
        }
    }

    public void addList(List<Hotel> hotelList) throws ServiceException {
        Connection connection = ConnectionUtil.getConnection();
        try {
            connection.setAutoCommit(false);
            HotelDao.getInstance().addList(hotelList);
            connection.commit();
            BookingSystemLogger.getInstance().logInfo(getClass(), ServiceMessage.TRANSACTION_SUCCEEDED);
        } catch (SQLException | LocalisationException e) {
            ExceptionHandler.handleSQLOrDaoException(connection, e, getClass());
        }
    }

    public List<Hotel> getByCountry(String country) throws ServiceException {
        Connection connection = ConnectionUtil.getConnection();
        List<Hotel> hotelList = null;
        try {
            connection.setAutoCommit(false);
            hotelList = HotelDao.getInstance().getByCountry(country);
            connection.commit();
            BookingSystemLogger.getInstance().logInfo(getClass(), ServiceMessage.TRANSACTION_SUCCEEDED);
        } catch (SQLException | LocalisationException e) {
            ExceptionHandler.handleSQLOrDaoException(connection, e, getClass());
        }
        return hotelList;
    }

    public List<Hotel> getByCity(String city) throws ServiceException {
        Connection connection = ConnectionUtil.getConnection();
        List<Hotel> hotelList = null;
        try {
            connection.setAutoCommit(false);
            hotelList = HotelDao.getInstance().getByCity(city);
            connection.commit();
            BookingSystemLogger.getInstance().logInfo(getClass(), ServiceMessage.TRANSACTION_SUCCEEDED);
        } catch (SQLException | LocalisationException e) {
            ExceptionHandler.handleSQLOrDaoException(connection, e, getClass());
        }
        return hotelList;
    }

    public HashMap<String, Set<String>> getFieldValues() throws ServiceException {
        Connection connection = ConnectionUtil.getConnection();
        HashMap<String, Set<String>> fieldsValuesMap = null;
        try {
            connection.setAutoCommit(false);
            fieldsValuesMap = HotelDao.getInstance().getFieldsValues();
            connection.commit();
            BookingSystemLogger.getInstance().logInfo(getClass(), ServiceMessage.TRANSACTION_SUCCEEDED);
        } catch (SQLException | LocalisationException e) {
            ExceptionHandler.handleSQLOrDaoException(connection, e, getClass());
        }
        return fieldsValuesMap;
    }
}
