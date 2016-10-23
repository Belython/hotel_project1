package by.kanarski.booking.services.impl;

import by.kanarski.booking.constants.ServiceMessage;
import by.kanarski.booking.dao.impl.RoomTypeDao;
import by.kanarski.booking.dto.RoomTypeDto;
import by.kanarski.booking.entities.RoomType;
import by.kanarski.booking.exceptions.DaoException;
import by.kanarski.booking.exceptions.ServiceException;
import by.kanarski.booking.services.interfaces.IRoomTypeService;
import by.kanarski.booking.utils.BookingSystemLogger;
import by.kanarski.booking.utils.ConnectionUtil;
import by.kanarski.booking.utils.DtoToEntityConverter;
import by.kanarski.booking.utils.ExceptionHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class RoomTypeServiceImpl implements IRoomTypeService {

    private static RoomTypeServiceImpl instance;
    private static RoomTypeDao roomTypeDao = RoomTypeDao.getInstance();

    private RoomTypeServiceImpl() {
    }

    public static synchronized RoomTypeServiceImpl getInstance() {
        if (instance == null) {
            instance = new RoomTypeServiceImpl();
        }
        return instance;
    }

    @Override
    public void add(RoomTypeDto roomTypeDto) throws ServiceException {

    }

    @Override
    public List<RoomTypeDto> getAll() throws ServiceException {
        Connection connection = ConnectionUtil.getConnection();
        List<RoomTypeDto> roomTypeDtoList = null;
        try {
            connection.setAutoCommit(false);
            List<RoomType> roomTypeList = roomTypeDao.getAll();
            roomTypeDtoList = DtoToEntityConverter.toRoomTypeDtoList(roomTypeList);
            connection.commit();
            BookingSystemLogger.getInstance().logInfo(getClass(), ServiceMessage.TRANSACTION_SUCCEEDED);
        } catch (SQLException | DaoException e) {
            ExceptionHandler.handleSQLOrDaoException(connection, e, getClass());
        }
        return roomTypeDtoList;
    }

    @Override
    public RoomTypeDto getById(long id) throws ServiceException {
        return null;
    }

    @Override
    public void update(RoomTypeDto roomTypeDto) throws ServiceException {
        Connection connection = ConnectionUtil.getConnection();
        try {
            connection.setAutoCommit(false);
            RoomType roomType = DtoToEntityConverter.toRoomType(roomTypeDto);
            roomTypeDao.update(roomType);
            connection.commit();
            BookingSystemLogger.getInstance().logInfo(getClass(), ServiceMessage.TRANSACTION_SUCCEEDED);
        } catch (SQLException | DaoException e) {
            ExceptionHandler.handleSQLOrDaoException(connection, e, getClass());
        }
    }

    @Override
    public void delete(long id) throws ServiceException {

    }

    public void updateList(List<RoomTypeDto> roomTypeDtoList) throws ServiceException {
        Connection connection = ConnectionUtil.getConnection();
        try {
            connection.setAutoCommit(false);
            List<RoomType> roomTypeList = DtoToEntityConverter.toRoomTypeList(roomTypeDtoList);
            roomTypeDao.updateList(roomTypeList);
            connection.commit();
            BookingSystemLogger.getInstance().logInfo(getClass(), ServiceMessage.TRANSACTION_SUCCEEDED);
        } catch (SQLException | DaoException e) {
            ExceptionHandler.handleSQLOrDaoException(connection, e, getClass());
        }
    }

    public void addList(List<RoomTypeDto> roomTypeDtoList) throws ServiceException {
        Connection connection = ConnectionUtil.getConnection();
        try {
            connection.setAutoCommit(false);
            List<RoomType> roomTypeList = DtoToEntityConverter.toRoomTypeList(roomTypeDtoList);
            roomTypeDao.addList(roomTypeList);
            connection.commit();
            BookingSystemLogger.getInstance().logInfo(getClass(), ServiceMessage.TRANSACTION_SUCCEEDED);
        } catch (SQLException | DaoException e) {
            ExceptionHandler.handleSQLOrDaoException(connection, e, getClass());
        }
    }
}
