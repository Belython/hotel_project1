package by.kanarski.booking.services.impl;

import by.kanarski.booking.constants.ServiceMessages;
import by.kanarski.booking.dao.impl.RoomDao;
import by.kanarski.booking.dto.OrderDto;
import by.kanarski.booking.entities.Bill;
import by.kanarski.booking.entities.Room;
import by.kanarski.booking.exceptions.DaoException;
import by.kanarski.booking.exceptions.ServiceException;
import by.kanarski.booking.services.interfaces.IRoomService;
import by.kanarski.booking.utils.BookingSystemLogger;
import by.kanarski.booking.utils.ConnectionUtil;
import by.kanarski.booking.utils.ExceptionHandler;
import by.kanarski.booking.utils.SerializationUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

public class RoomServiceImpl implements IRoomService {
    private static RoomServiceImpl instance;

    private RoomServiceImpl() {
    }

    public static synchronized RoomServiceImpl getInstance() {
        if (instance == null) {
            instance = new RoomServiceImpl();
        }
        return instance;
    }

    @Override
    public void add(Room entity) throws ServiceException {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Room> getAll() throws ServiceException {
        Connection connection = ConnectionUtil.getConnection();
        List<Room> rooms = null;
        try {
            connection.setAutoCommit(false);
            rooms = RoomDao.getInstance().getAll();
            connection.commit();
            BookingSystemLogger.getInstance().logError(getClass(), ServiceMessages.TRANSACTION_SUCCEEDED);
        } catch (SQLException | DaoException e) {
            ExceptionHandler.handleSQLOrDaoException(connection, e, getClass());
        }
        return rooms;
    }

    @Override
    public Room getById(long id) throws ServiceException {
        Connection connection = ConnectionUtil.getConnection();
        Room room = null;
        try {
            connection.setAutoCommit(false);
            room = RoomDao.getInstance().getById(id);
            connection.commit();
            BookingSystemLogger.getInstance().logError(getClass(), ServiceMessages.TRANSACTION_SUCCEEDED);
        } catch (SQLException | DaoException e) {
            ExceptionHandler.handleSQLOrDaoException(connection, e, getClass());
        }
        return room;
    }

    @Override
    public void update(Room room) throws ServiceException {
        Connection connection = ConnectionUtil.getConnection();
        try {
            connection.setAutoCommit(false);
            RoomDao.getInstance().update(room);
            connection.commit();
            BookingSystemLogger.getInstance().logError(getClass(), ServiceMessages.TRANSACTION_SUCCEEDED);
        } catch (SQLException | DaoException e) {
            ExceptionHandler.handleSQLOrDaoException(connection, e, getClass());
        }
    }

    @Override
    public void delete(long id) throws ServiceException {

    }

    public List<Room> getAvailableRooms(OrderDto orderDto) throws ServiceException {
        Connection connection = ConnectionUtil.getConnection();
        List<Room> rooms = new ArrayList<>();
        long checkInDate = orderDto.getCheckInDate();
        long checkOutDate = orderDto.getCheckOutDate();
        try {
            connection.setAutoCommit(false);
            List<Room> roomsForAllDates = RoomDao.getInstance().getAvailableRooms(orderDto);
            for (Room room : roomsForAllDates) {
                TreeMap<Long, Long> bookedDates = room.getBookedDates();
                if (bookedDates == null) {
                    rooms.add(room);
                } else {
                    NavigableSet<Long> bookingStartSet = bookedDates.navigableKeySet();
                    Long bookingStart = bookingStartSet.ceiling(checkOutDate);
                    if (bookingStart == null) {
                        Long lowerBookingStart = bookingStartSet.floor(checkOutDate);
                        Long lowerBookingEnd = bookedDates.get(lowerBookingStart);
                        if (lowerBookingEnd < checkInDate) {
                            rooms.add(room);
                        }
                    } else {
                        Long prevBookingStart = bookedDates.lowerKey(bookingStart);
                        Long prevBookingEnd = bookedDates.get(prevBookingStart);
                        if (prevBookingEnd < checkInDate) {
                            rooms.add(room);
                        }
                    }
                }
            }
            connection.commit();
            BookingSystemLogger.getInstance().logError(getClass(), ServiceMessages.TRANSACTION_SUCCEEDED);
        } catch (SQLException | DaoException e) {
            ExceptionHandler.handleSQLOrDaoException(connection, e, getClass());
        }
        return rooms;
    }

    public List<Room> getByHotelId(long hotelId) throws ServiceException {
        Connection connection = ConnectionUtil.getConnection();
        List<Room> rooms = null;
        try {
            connection.setAutoCommit(false);
            rooms = RoomDao.getInstance().getByHotelId(hotelId);
            connection.commit();
            BookingSystemLogger.getInstance().logError(getClass(), ServiceMessages.TRANSACTION_SUCCEEDED);
        } catch (SQLException | DaoException e) {
            ExceptionHandler.handleSQLOrDaoException(connection, e, getClass());
        }
        return rooms;
    }

    public List<Room> getByBill(Bill bill) throws ServiceException {
        Connection connection = ConnectionUtil.getConnection();
        List<Room> roomList = null;
        try {
            connection.setAutoCommit(false);
            roomList = RoomDao.getInstance().getByIdList(bill.getBookedRoomIdList());
            connection.commit();
            BookingSystemLogger.getInstance().logError(getClass(), ServiceMessages.TRANSACTION_SUCCEEDED);
        } catch (SQLException | DaoException e) {
            ExceptionHandler.handleSQLOrDaoException(connection, e, getClass());
        }
        return roomList;
    }

    public void updateList(List<Room> roomList) throws ServiceException {
        Connection connection = ConnectionUtil.getConnection();
        try {
            connection.setAutoCommit(false);
            RoomDao.getInstance().updateList(roomList);
            connection.commit();
            BookingSystemLogger.getInstance().logError(getClass(), ServiceMessages.TRANSACTION_SUCCEEDED);
        } catch (SQLException | DaoException e) {
            ExceptionHandler.handleSQLOrDaoException(connection, e, getClass());
        }
    }

    public void addList(List<Room> roomList) throws ServiceException {
        Connection connection = ConnectionUtil.getConnection();
        try {
            connection.setAutoCommit(false);
            RoomDao.getInstance().addList(roomList);
            connection.commit();
            BookingSystemLogger.getInstance().logError(getClass(), ServiceMessages.TRANSACTION_SUCCEEDED);
        } catch (SQLException | DaoException e) {
            ExceptionHandler.handleSQLOrDaoException(connection, e, getClass());
        }
    }

    public void reserveRoomList(List<Room> roomList) throws ServiceException {
        Connection connection = ConnectionUtil.getConnection();
        try {
            connection.setAutoCommit(false);
            RoomDao.getInstance().reserveRoomList(roomList);
            connection.commit();
            BookingSystemLogger.getInstance().logError(getClass(), ServiceMessages.TRANSACTION_SUCCEEDED);
        } catch (SQLException | DaoException e) {
            ExceptionHandler.handleSQLOrDaoException(connection, e, getClass());
        }
    }

    public List<Room> getByIdList(List<Long> roomIdList) throws ServiceException {
        Connection connection = ConnectionUtil.getConnection();
        List<Room> rooms = null;
        try {
            connection.setAutoCommit(false);
            rooms = RoomDao.getInstance().getByIdList(roomIdList);
            connection.commit();
            BookingSystemLogger.getInstance().logError(getClass(), ServiceMessages.TRANSACTION_SUCCEEDED);
        } catch (SQLException | DaoException e) {
            ExceptionHandler.handleSQLOrDaoException(connection, e, getClass());
        }
        return rooms;
    }

}
