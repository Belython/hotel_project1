package by.kanarski.booking.services.impl;

import by.kanarski.booking.constants.ServiceMessage;
import by.kanarski.booking.dao.impl.RoomDao;
import by.kanarski.booking.dto.BillDto;
import by.kanarski.booking.dto.OrderDto;
import by.kanarski.booking.dto.RoomDto;
import by.kanarski.booking.dto.UserDto;
import by.kanarski.booking.entities.Bill;
import by.kanarski.booking.entities.Room;
import by.kanarski.booking.entities.User;
import by.kanarski.booking.exceptions.LocalisationException;
import by.kanarski.booking.exceptions.ServiceException;
import by.kanarski.booking.services.interfaces.IRoomService;
import by.kanarski.booking.utils.*;
import sun.util.locale.LocaleUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

public class RoomServiceImpl implements IRoomService {
    private static RoomServiceImpl instance;
    private RoomDao roomDao = RoomDao.getInstance();
    private Locale basicLocale = DateUtil.LOCALE_EN_US;
    private Currency basicCurrency = CurrencyUtil.CURRENCY_USD;

    private RoomServiceImpl() {
    }

    public static synchronized RoomServiceImpl getInstance() {
        if (instance == null) {
            instance = new RoomServiceImpl();
        }
        return instance;
    }

    @Override
    public void add(RoomDto roomDto) throws ServiceException {
        throw new UnsupportedOperationException();
    }

    @Override
    public RoomDto getById(long id) throws ServiceException {
        Connection connection = ConnectionUtil.getConnection();
        RoomDto roomDto = null;
        try {
            connection.setAutoCommit(false);
            Room room = roomDao.getById(id);
            roomDto = convertToRoomDto(room);
            connection.commit();
        } catch (SQLException | LocalisationException e) {
            ExceptionHandler.handleSQLOrDaoException(connection, e, getClass());
        }
        return roomDto;
    }

    @Override
    public List<RoomDto> getAll() throws ServiceException {
        Connection connection = ConnectionUtil.getConnection();
        List<RoomDto> roomDtoList = null;
        try {
            connection.setAutoCommit(false);
            List<Room> roomList = roomDao.getAll();
            roomDtoList = convertToRoomDtoList(roomList);
            connection.commit();
        } catch (SQLException | LocalisationException e) {
            ExceptionHandler.handleSQLOrDaoException(connection, e, getClass());
        }
        return roomDtoList;
    }

    @Override
    public void update(RoomDto roomDto) throws ServiceException {
        Connection connection = ConnectionUtil.getConnection();
        try {
            Room room = converToRoom(roomDto);
            connection.setAutoCommit(false);
            roomDao.update(room);
            connection.commit();
            BookingSystemLogger.getInstance().logInfo(getClass(), ServiceMessage.TRANSACTION_SUCCEEDED);
        } catch (SQLException | LocalisationException e) {
            ExceptionHandler.handleSQLOrDaoException(connection, e, getClass());
        }
    }

    @Override
    public void delete(long id) throws ServiceException {

    }

    public List<RoomDto> getAvailableRooms(OrderDto orderDto) throws ServiceException {
        Connection connection = ConnectionUtil.getConnection();
        List<RoomDto> roomDtoList = null;
        long checkInDate = orderDto.getCheckInDate();
        long checkOutDate = orderDto.getCheckOutDate();
        try {

            connection.setAutoCommit(false);
            List<Room> roomsForAllDates = roomDao.getAvailableRooms(orderDto);
            List<Room> roomList = new ArrayList<>();
            for (Room room : roomsForAllDates) {
                TreeMap<Long, Long> bookedDates = room.getBookedDates();
                if ((bookedDates == null) || (bookedDates.size() == 0)) {
                    roomList.add(room);
                } else {
                    NavigableSet<Long> bookingStartSet = bookedDates.navigableKeySet();
                    Long bookingStart = bookingStartSet.ceiling(checkOutDate);
                    if (bookingStart == null) {
                        Long lowerBookingStart = bookingStartSet.floor(checkOutDate);
                        Long lowerBookingEnd = bookedDates.get(lowerBookingStart);
                        if (lowerBookingEnd < checkInDate) {
                            roomList.add(room);
                        }
                    } else {
                        Long prevBookingStart = bookedDates.lowerKey(bookingStart);
                        Long prevBookingEnd = bookedDates.get(prevBookingStart);
                        if (prevBookingEnd < checkInDate) {
                            roomList.add(room);
                        }
                    }
                }
            }
            roomDtoList = convertToRoomDtoList(roomList);
            connection.commit();
            BookingSystemLogger.getInstance().logInfo(getClass(), ServiceMessage.TRANSACTION_SUCCEEDED);
        } catch (SQLException | LocalisationException e) {
            ExceptionHandler.handleSQLOrDaoException(connection, e, getClass());
        }
        return roomDtoList;
    }

    public List<RoomDto> getByHotelId(long hotelId) throws ServiceException {
        Connection connection = ConnectionUtil.getConnection();
        List<RoomDto> roomDtoList = null;
        try {
            connection.setAutoCommit(false);
            List<Room> roomList = roomDao.getByHotelId(hotelId);
            roomDtoList = convertToRoomDtoList(roomList);
            connection.commit();
            BookingSystemLogger.getInstance().logInfo(getClass(), ServiceMessage.TRANSACTION_SUCCEEDED);
        } catch (SQLException | LocalisationException e) {
            ExceptionHandler.handleSQLOrDaoException(connection, e, getClass());
        }
        return roomDtoList;
    }

    public List<RoomDto> getByBill(BillDto billDto) throws ServiceException {
        Connection connection = ConnectionUtil.getConnection();
        List<RoomDto> roomDtoList = null;
        Bill bill = DtoToEntityConverter.tobill
        try {
            connection.setAutoCommit(false);
            List<Room> roomList = roomDao.getByIdList(billDto.getBookedRoomIdList());
            connection.commit();
            BookingSystemLogger.getInstance().logInfo(getClass(), ServiceMessage.TRANSACTION_SUCCEEDED);
        } catch (SQLException | LocalisationException e) {
            ExceptionHandler.handleSQLOrDaoException(connection, e, getClass());
        }
        return roomDtoList;
    }

    public void updateList(List<Room> roomList) throws ServiceException {
        Connection connection = ConnectionUtil.getConnection();
        try {
            connection.setAutoCommit(false);
            roomDao.updateList(roomList);
            connection.commit();
            BookingSystemLogger.getInstance().logInfo(getClass(), ServiceMessage.TRANSACTION_SUCCEEDED);
        } catch (SQLException | LocalisationException e) {
            ExceptionHandler.handleSQLOrDaoException(connection, e, getClass());
        }
    }

    public void addList(List<Room> roomList) throws ServiceException {
        Connection connection = ConnectionUtil.getConnection();
        try {
            connection.setAutoCommit(false);
            roomDao.addList(roomList);
            connection.commit();
            BookingSystemLogger.getInstance().logInfo(getClass(), ServiceMessage.TRANSACTION_SUCCEEDED);
        } catch (SQLException | LocalisationException e) {
            ExceptionHandler.handleSQLOrDaoException(connection, e, getClass());
        }
    }

    public void reserveRoomList(List<Room> roomList) throws ServiceException {
        Connection connection = ConnectionUtil.getConnection();
        try {
            connection.setAutoCommit(false);
            roomDao.reserveRoomList(roomList);
            connection.commit();
            BookingSystemLogger.getInstance().logInfo(getClass(), ServiceMessage.TRANSACTION_SUCCEEDED);
        } catch (SQLException | LocalisationException e) {
            ExceptionHandler.handleSQLOrDaoException(connection, e, getClass());
        }
    }

    public List<Room> getByIdList(List<Long> roomIdList) throws ServiceException {
        Connection connection = ConnectionUtil.getConnection();
        List<Room> rooms = null;
        try {
            connection.setAutoCommit(false);
            rooms = roomDao.getByIdList(roomIdList);
            connection.commit();
            BookingSystemLogger.getInstance().logInfo(getClass(), ServiceMessage.TRANSACTION_SUCCEEDED);
        } catch (SQLException | LocalisationException e) {
            ExceptionHandler.handleSQLOrDaoException(connection, e, getClass());
        }
        return rooms;
    }

    private Room converToRoom(RoomDto roomDto) throws LocalisationException {
        Room room = DtoToEntityConverter.toRoom(roomDto, basicLocale, basicCurrency);
        return room;
    }

    private List<Room> roomList(List<RoomDto> roomDtoList) throws LocalisationException {
        List<Room> roomList = DtoToEntityConverter.toRoomList(roomDtoList, basicLocale, basicCurrency);
        return roomList;
    }

    private RoomDto convertToRoomDto(Room room) throws LocalisationException {
        RoomDto roomDto = DtoToEntityConverter.toRoomDto(room, basicLocale, basicCurrency);
        return  roomDto;
    }
    
    private List<RoomDto> convertToRoomDtoList(List<Room> roomList) throws LocalisationException {
        List<RoomDto> roomDtoList = DtoToEntityConverter.toRoomDtoList(roomList, basicLocale, basicCurrency);
        return roomDtoList;
    }

}
