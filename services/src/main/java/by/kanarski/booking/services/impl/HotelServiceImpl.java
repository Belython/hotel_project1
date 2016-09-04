package by.kanarski.booking.services.impl;

import by.kanarski.booking.constants.DaoMessages;
import by.kanarski.booking.constants.ServiceMessages;
import by.kanarski.booking.dao.impl.HotelDao;
import by.kanarski.booking.entities.Hotel;
import by.kanarski.booking.exceptions.DaoException;
import by.kanarski.booking.exceptions.ServiceException;
import by.kanarski.booking.services.interfaces.IHotelService;
import by.kanarski.booking.utils.BookingSystemLogger;
import by.kanarski.booking.utils.ConnectionUtil;
import by.kanarski.booking.utils.ExceptionHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


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
        List<Hotel> hotelList = new ArrayList<>();
        try {
            connection.setAutoCommit(false);
            hotelList = HotelDao.getInstance().getAll();
            connection.commit();
            BookingSystemLogger.getInstance().logError(getClass(), ServiceMessages.TRANSACTION_SUCCEEDED);
        } catch (SQLException | DaoException e) {
            ExceptionHandler.handleSQLOrDaoException(connection, e, getClass());
        }
        return hotelList;
    }

    @Override
    public Hotel getById(long id) throws ServiceException {
        return null;
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
            BookingSystemLogger.getInstance().logError(getClass(), ServiceMessages.TRANSACTION_SUCCEEDED);
        } catch (SQLException | DaoException e) {
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
            BookingSystemLogger.getInstance().logError(getClass(), ServiceMessages.TRANSACTION_SUCCEEDED);
        } catch (SQLException | DaoException e) {
            ExceptionHandler.handleSQLOrDaoException(connection, e, getClass());
        }
    }
}
