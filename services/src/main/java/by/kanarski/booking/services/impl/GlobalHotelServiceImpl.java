package by.kanarski.booking.services.impl;

import by.kanarski.booking.constants.ServiceMessage;
import by.kanarski.booking.dao.impl.HotelDao;
import by.kanarski.booking.dao.impl.RoomDao;
import by.kanarski.booking.dto.GlobalHotelDto;
import by.kanarski.booking.dto.OrderDto;
import by.kanarski.booking.entities.Hotel;
import by.kanarski.booking.entities.Room;
import by.kanarski.booking.exceptions.DaoException;
import by.kanarski.booking.exceptions.LocalisationException;
import by.kanarski.booking.exceptions.ServiceException;
import by.kanarski.booking.services.interfaces.IGlobalHotelService;
import by.kanarski.booking.utils.BookingSystemLogger;
import by.kanarski.booking.utils.ConnectionUtil;
import by.kanarski.booking.utils.DtoToEntityConverter;
import by.kanarski.booking.utils.ExceptionHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class GlobalHotelServiceImpl implements IGlobalHotelService {

    private static GlobalHotelServiceImpl instance;
    private static HotelDao hotelDao = HotelDao.getInstance();
    private static RoomDao roomDao = RoomDao.getInstance();

    private GlobalHotelServiceImpl() {
    }

    public static synchronized GlobalHotelServiceImpl getInstance() {
        if (instance == null) {
            instance = new GlobalHotelServiceImpl();
        }
        return instance;
    }

    @Override
    public void add(GlobalHotelDto globalHotelDto) throws ServiceException {

    }

    @Override
    public GlobalHotelDto getById(long id) throws ServiceException {
        Connection connection = ConnectionUtil.getConnection();
        GlobalHotelDto globalHotelDto = null;
        try {
            connection.setAutoCommit(false);
            Hotel hotel = hotelDao.getById(id);

            globalHotelDto = toGlobalHotelDto(hotel);
            connection.commit();
            BookingSystemLogger.getInstance().logInfo(getClass(), ServiceMessage.TRANSACTION_SUCCEEDED);
        } catch (SQLException | LocalisationException | DaoException e) {
            ExceptionHandler.handleSQLOrDaoException(connection, e, getClass());
        }
        return globalHotelDto;
    }

    @Override
    public List<GlobalHotelDto> getAll() throws ServiceException {
        Connection connection = ConnectionUtil.getConnection();
        List<GlobalHotelDto> globalHotelDtoList = null;
        try {
            connection.setAutoCommit(false);
            List<Hotel> hotelList = hotelDao.getAll();
            globalHotelDtoList = toGlobalHotelDtoList(hotelList);
            connection.commit();
            BookingSystemLogger.getInstance().logInfo(getClass(), ServiceMessage.TRANSACTION_SUCCEEDED);
        } catch (SQLException | LocalisationException | DaoException e) {
            ExceptionHandler.handleSQLOrDaoException(connection, e, getClass());
        }
        return globalHotelDtoList;
    }

    @Override
    public void update(GlobalHotelDto entity) throws ServiceException {

    }

    @Override
    public void delete(long id) throws ServiceException {

    }

    public GlobalHotelDto getByHotelName(String hotelName) throws ServiceException {
        Connection connection = ConnectionUtil.getConnection();
        GlobalHotelDto globalHotelDto = null;
        try {
            connection.setAutoCommit(false);
            Hotel hotel = hotelDao.getByHotelName(hotelName);
            globalHotelDto = toGlobalHotelDto(hotel);
            connection.commit();
            BookingSystemLogger.getInstance().logInfo(getClass(), ServiceMessage.TRANSACTION_SUCCEEDED);
        } catch (SQLException | LocalisationException | DaoException e) {
            ExceptionHandler.handleSQLOrDaoException(connection, e, getClass());
        }
        return globalHotelDto;
    }

    public void updateList(List<GlobalHotelDto> globalHotelDtoList) throws ServiceException {
        Connection connection = ConnectionUtil.getConnection();
        try {
            connection.setAutoCommit(false);
            List<Hotel> hotelList = DtoToEntityConverter.toHotelListG(globalHotelDtoList);
            hotelDao.updateList(hotelList);
            connection.commit();
            BookingSystemLogger.getInstance().logInfo(getClass(), ServiceMessage.TRANSACTION_SUCCEEDED);
        } catch (SQLException | DaoException e) {
            ExceptionHandler.handleSQLOrDaoException(connection, e, getClass());
        }
    }

    public void addList(List<GlobalHotelDto> globalHotelDtoList) throws ServiceException {
        Connection connection = ConnectionUtil.getConnection();
        try {
            connection.setAutoCommit(false);
            List<Hotel> hotelList = DtoToEntityConverter.toHotelListG(globalHotelDtoList);
            hotelDao.addList(hotelList);
            connection.commit();
            BookingSystemLogger.getInstance().logInfo(getClass(), ServiceMessage.TRANSACTION_SUCCEEDED);
        } catch (SQLException | DaoException e) {
            ExceptionHandler.handleSQLOrDaoException(connection, e, getClass());
        }
    }

    public List<GlobalHotelDto> getByCountry(String country) throws ServiceException {
        Connection connection = ConnectionUtil.getConnection();
        List<GlobalHotelDto> globalHotelDtoList = null;
        try {
            connection.setAutoCommit(false);
            List<Hotel> hotelList = hotelDao.getByCountry(country);
            globalHotelDtoList = toGlobalHotelDtoList(hotelList);
            connection.commit();
            BookingSystemLogger.getInstance().logInfo(getClass(), ServiceMessage.TRANSACTION_SUCCEEDED);
        } catch (SQLException | LocalisationException | DaoException e) {
            ExceptionHandler.handleSQLOrDaoException(connection, e, getClass());
        }
        return globalHotelDtoList;
    }

    public List<GlobalHotelDto> getByCity(String city) throws ServiceException {
        Connection connection = ConnectionUtil.getConnection();
        List<GlobalHotelDto> globalHotelDtoList = null;
        try {
            connection.setAutoCommit(false);
            List<Hotel> hotelList = hotelDao.getByCity(city);
            globalHotelDtoList = toGlobalHotelDtoList(hotelList);
            connection.commit();
            BookingSystemLogger.getInstance().logInfo(getClass(), ServiceMessage.TRANSACTION_SUCCEEDED);
        } catch (SQLException | LocalisationException | DaoException e) {
            ExceptionHandler.handleSQLOrDaoException(connection, e, getClass());
        }
        return globalHotelDtoList;
    }

    public List<GlobalHotelDto> getByOrder(OrderDto orderDto) throws ServiceException {
        Connection connection = ConnectionUtil.getConnection();
        List<GlobalHotelDto> globalHotelDtoList =  new ArrayList<>();
        try {
            connection.setAutoCommit(false);
            List<Room> availableRoomList = roomDao.getAvailableRooms(orderDto);
            int separator = 0;
            for (int i = 0; i < availableRoomList.size(); i++) {
                if (i < (availableRoomList.size() - 1)) {
                    String curHotelName = availableRoomList.get(i).getHotel().getHotelName();
                    String nextHotelName = availableRoomList.get(i + 1).getHotel().getHotelName();
                    if (!curHotelName.equals(nextHotelName)) {
                        Hotel hotel = availableRoomList.get(i).getHotel();
                        List<Room> roomList = availableRoomList.subList(separator, i + 1);
                        GlobalHotelDto globalHotelDto = DtoToEntityConverter.toGlobalHotelDto(hotel, roomList);
                        globalHotelDtoList.add(globalHotelDto);
                        separator = i + 1;
                    }
                } else {
                    Hotel hotel = availableRoomList.get(i).getHotel();
                    List<Room> roomList = availableRoomList.subList(separator, i + 1);
                    GlobalHotelDto globalHotelDto = DtoToEntityConverter.toGlobalHotelDto(hotel, roomList);
                    globalHotelDtoList.add(globalHotelDto);
                    separator = i + 1;
                }
            }
            connection.commit();
            BookingSystemLogger.getInstance().logInfo(getClass(), ServiceMessage.TRANSACTION_SUCCEEDED);
        } catch (SQLException | LocalisationException | DaoException e) {
            ExceptionHandler.handleSQLOrDaoException(connection, e, getClass());
        }
        return globalHotelDtoList;
    }

    public GlobalHotelDto getByOrder1(OrderDto orderDto) throws ServiceException {
        Connection connection = ConnectionUtil.getConnection();
        GlobalHotelDto globalHotelDto = null;
        try {
            connection.setAutoCommit(false);
            Hotel hotel = HotelDao.getInstance().getById(orderDto.getHotel().getHotelId());
            List<Room> availableRoomList = roomDao.getAvailableRooms(orderDto);
            globalHotelDto = DtoToEntityConverter.toGlobalHotelDto(hotel, availableRoomList);
            connection.commit();
            BookingSystemLogger.getInstance().logInfo(getClass(), ServiceMessage.TRANSACTION_SUCCEEDED);
        } catch (SQLException | LocalisationException | DaoException e) {
            ExceptionHandler.handleSQLOrDaoException(connection, e, getClass());
        }
        return globalHotelDto;
    }

    private GlobalHotelDto toGlobalHotelDto(Hotel hotel)
            throws LocalisationException, DaoException {
        List<Room> roomList = roomDao.getByHotelId(hotel.getHotelId());
        GlobalHotelDto globalHotelDto = DtoToEntityConverter.toGlobalHotelDto(hotel, roomList);
        return globalHotelDto;
    }

    private List<GlobalHotelDto> toGlobalHotelDtoList(List<Hotel> hotelList)
            throws LocalisationException, DaoException {
        List<GlobalHotelDto> globalHotelDtoList = new ArrayList<>();
        for (Hotel hotel : hotelList) {
            GlobalHotelDto globalHotelDto = toGlobalHotelDto(hotel);
            globalHotelDtoList.add(globalHotelDto);
        }
        return globalHotelDtoList;
    }


}
