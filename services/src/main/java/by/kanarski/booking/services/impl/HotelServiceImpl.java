package by.kanarski.booking.services.impl;

import by.kanarski.booking.constants.ServiceMessage;
import by.kanarski.booking.dao.impl.HotelDao;
import by.kanarski.booking.dto.HotelDto;
import by.kanarski.booking.entities.Hotel;
import by.kanarski.booking.exceptions.DaoException;
import by.kanarski.booking.exceptions.ServiceException;
import by.kanarski.booking.services.interfaces.IHotelService;
import by.kanarski.booking.utils.BookingSystemLogger;
import by.kanarski.booking.utils.ConnectionUtil;
import by.kanarski.booking.utils.DtoToEntityConverter;
import by.kanarski.booking.utils.ExceptionHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


public class HotelServiceImpl implements IHotelService {

    private static HotelServiceImpl instance;
    private static HotelDao hotelDao = HotelDao.getInstance();

    private HotelServiceImpl() {
    }

    public static synchronized HotelServiceImpl getInstance() {
        if (instance == null) {
            instance = new HotelServiceImpl();
        }
        return instance;
    }

    @Override
    public void add(HotelDto hotelDto) throws ServiceException {

    }

    @Override
    public HotelDto getById(long id) throws ServiceException {
        Connection connection = ConnectionUtil.getConnection();
        HotelDto hotelDto = null;
        try {
            connection.setAutoCommit(false);
            Hotel hotel = hotelDao.getById(id);
            hotelDto = DtoToEntityConverter.toHotelDto(hotel);
            connection.commit();
            BookingSystemLogger.getInstance().logInfo(getClass(), ServiceMessage.TRANSACTION_SUCCEEDED);
        } catch (SQLException | DaoException e) {
            ExceptionHandler.handleSQLOrDaoException(connection, e, getClass());
        }
        return hotelDto;
    }

    @Override
    public List<HotelDto> getAll() throws ServiceException {
        Connection connection = ConnectionUtil.getConnection();
        List<HotelDto> hotelDtoList = null;
        try {
            connection.setAutoCommit(false);
            List<Hotel> hotelList = hotelDao.getAll();
            hotelDtoList = DtoToEntityConverter.toHotelDtoList(hotelList);
            connection.commit();
            BookingSystemLogger.getInstance().logInfo(getClass(), ServiceMessage.TRANSACTION_SUCCEEDED);
        } catch (SQLException | DaoException e) {
            ExceptionHandler.handleSQLOrDaoException(connection, e, getClass());
        }
        return hotelDtoList;
    }

    @Override
    public void update(HotelDto entity) throws ServiceException {

    }

    @Override
    public void delete(long id) throws ServiceException {

    }

    public HotelDto getByHotelName(String hotelName) throws ServiceException {
        Connection connection = ConnectionUtil.getConnection();
        HotelDto hotelDto = null;
        try {
            connection.setAutoCommit(false);
            Hotel hotel = hotelDao.getByHotelName(hotelName);
            hotelDto = DtoToEntityConverter.toHotelDto(hotel);
            connection.commit();
            BookingSystemLogger.getInstance().logInfo(getClass(), ServiceMessage.TRANSACTION_SUCCEEDED);
        } catch (SQLException | DaoException e) {
            ExceptionHandler.handleSQLOrDaoException(connection, e, getClass());
        }
        return hotelDto;
    }

    public void updateList(List<HotelDto> hotelDtoList) throws ServiceException {
        Connection connection = ConnectionUtil.getConnection();
        try {
            connection.setAutoCommit(false);
            List<Hotel> hotelList = DtoToEntityConverter.toHotelList(hotelDtoList);
            hotelDao.updateList(hotelList);
            connection.commit();
            BookingSystemLogger.getInstance().logInfo(getClass(), ServiceMessage.TRANSACTION_SUCCEEDED);
        } catch (SQLException | DaoException e) {
            ExceptionHandler.handleSQLOrDaoException(connection, e, getClass());
        }
    }

    public void addList(List<HotelDto> hotelDtoList) throws ServiceException {
        Connection connection = ConnectionUtil.getConnection();
        try {
            connection.setAutoCommit(false);
            List<Hotel> hotelList = DtoToEntityConverter.toHotelList(hotelDtoList);
            hotelDao.addList(hotelList);
            connection.commit();
            BookingSystemLogger.getInstance().logInfo(getClass(), ServiceMessage.TRANSACTION_SUCCEEDED);
        } catch (SQLException | DaoException e) {
            ExceptionHandler.handleSQLOrDaoException(connection, e, getClass());
        }
    }

    public List<HotelDto> getByCountry(String country) throws ServiceException {
        Connection connection = ConnectionUtil.getConnection();
        List<HotelDto> hotelDtoList = null;
        try {
            connection.setAutoCommit(false);
            List<Hotel> hotelList = hotelDao.getByCountry(country);
            hotelDtoList = DtoToEntityConverter.toHotelDtoList(hotelList);
            connection.commit();
            BookingSystemLogger.getInstance().logInfo(getClass(), ServiceMessage.TRANSACTION_SUCCEEDED);
        } catch (SQLException | DaoException e) {
            ExceptionHandler.handleSQLOrDaoException(connection, e, getClass());
        }
        return hotelDtoList;
    }

    public List<HotelDto> getByCity(String city) throws ServiceException {
        Connection connection = ConnectionUtil.getConnection();
        List<HotelDto> hotelDtoList = null;
        try {
            connection.setAutoCommit(false);
            List<Hotel> hotelList = hotelDao.getByCity(city);
            hotelDtoList = DtoToEntityConverter.toHotelDtoList(hotelList);
            connection.commit();
            BookingSystemLogger.getInstance().logInfo(getClass(), ServiceMessage.TRANSACTION_SUCCEEDED);
        } catch (SQLException | DaoException e) {
            ExceptionHandler.handleSQLOrDaoException(connection, e, getClass());
        }
        return hotelDtoList;
    }

//    public HashMap<String, Set<String>> getFieldValues() throws ServiceException {
//        Connection connection = ConnectionUtil.getConnection();
//        HashMap<String, Set<String>> fieldsValuesMap = null;
//        try {
//            connection.setAutoCommit(false);
//            fieldsValuesMap = hotelDao.getFieldsValues();
//            connection.commit();
//            BookingSystemLogger.getInstance().logInfo(getClass(), ServiceMessage.TRANSACTION_SUCCEEDED);
//        } catch (SQLException | DaoException e) {
//            ExceptionHandler.handleSQLOrDaoException(connection, e, getClass());
//        }
//        return fieldsValuesMap;
//    }
}
