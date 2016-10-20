package by.kanarski.booking.services.impl;

import by.kanarski.booking.constants.ServiceMessage;
import by.kanarski.booking.dao.impl.RoomTypeDao;
import by.kanarski.booking.entities.RoomType;
import by.kanarski.booking.exceptions.LocalisationException;
import by.kanarski.booking.exceptions.ServiceException;
import by.kanarski.booking.services.interfaces.IRoomTypeService;
import by.kanarski.booking.utils.BookingSystemLogger;
import by.kanarski.booking.utils.ConnectionUtil;
import by.kanarski.booking.utils.ExceptionHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class RoomTypeServiceImpl implements IRoomTypeService {
    private static RoomTypeServiceImpl instance;

    private RoomTypeServiceImpl() {
    }

    public static synchronized RoomTypeServiceImpl getInstance() {
        if (instance == null) {
            instance = new RoomTypeServiceImpl();
        }
        return instance;
    }

    @Override
    public void add(RoomType entity) throws ServiceException {

    }

    @Override
    public List<RoomType> getAll() throws ServiceException {
        Connection connection = ConnectionUtil.getConnection();
        List<RoomType> roomTypes = null;
        try {
            connection.setAutoCommit(false);
            roomTypes = RoomTypeDao.getInstance().getAll();
            connection.commit();
            BookingSystemLogger.getInstance().logInfo(getClass(), ServiceMessage.TRANSACTION_SUCCEEDED);
        } catch (SQLException | LocalisationException e) {
            ExceptionHandler.handleSQLOrDaoException(connection, e, getClass());
        }
        return roomTypes;
    }

    @Override
    public RoomType getById(long id) throws ServiceException {
        return null;
    }

    @Override
    public void update(RoomType roomType) throws ServiceException {
        Connection connection = ConnectionUtil.getConnection();
        try {
            connection.setAutoCommit(false);
            RoomTypeDao.getInstance().update(roomType);
            connection.commit();
            BookingSystemLogger.getInstance().logInfo(getClass(), ServiceMessage.TRANSACTION_SUCCEEDED);
        } catch (SQLException | LocalisationException e) {
            ExceptionHandler.handleSQLOrDaoException(connection, e, getClass());
        }
    }

    @Override
    public void delete(long id) throws ServiceException {

    }

    public void updateList(List<RoomType> roomTypeList) throws ServiceException {
        Connection connection = ConnectionUtil.getConnection();
        try {
            connection.setAutoCommit(false);
            RoomTypeDao.getInstance().updateList(roomTypeList);
            connection.commit();
            BookingSystemLogger.getInstance().logInfo(getClass(), ServiceMessage.TRANSACTION_SUCCEEDED);
        } catch (SQLException | LocalisationException e) {
            ExceptionHandler.handleSQLOrDaoException(connection, e, getClass());
        }
    }

    public void addList(List<RoomType> roomTypeList) throws ServiceException {
        Connection connection = ConnectionUtil.getConnection();
        try {
            connection.setAutoCommit(false);
            RoomTypeDao.getInstance().addList(roomTypeList);
            connection.commit();
            BookingSystemLogger.getInstance().logInfo(getClass(), ServiceMessage.TRANSACTION_SUCCEEDED);
        } catch (SQLException | LocalisationException e) {
            ExceptionHandler.handleSQLOrDaoException(connection, e, getClass());
        }
    }
}
