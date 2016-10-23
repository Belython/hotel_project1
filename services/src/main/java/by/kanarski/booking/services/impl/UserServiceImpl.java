package by.kanarski.booking.services.impl;

import by.kanarski.booking.constants.ServiceMessage;
import by.kanarski.booking.dao.impl.UserDao;
import by.kanarski.booking.dto.UserDto;
import by.kanarski.booking.entities.User;
import by.kanarski.booking.exceptions.DaoException;
import by.kanarski.booking.exceptions.ServiceException;
import by.kanarski.booking.services.interfaces.IUserService;
import by.kanarski.booking.utils.BookingSystemLogger;
import by.kanarski.booking.utils.ConnectionUtil;
import by.kanarski.booking.utils.DtoToEntityConverter;
import by.kanarski.booking.utils.ExceptionHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements IUserService {
    
    private static UserServiceImpl instance;
    private static UserDao userDao = UserDao.getInstance();

    private UserServiceImpl() {
    }

    public static synchronized UserServiceImpl getInstance() {
        if (instance == null) {
            instance = new UserServiceImpl();
        }
        return instance;
    }

    @Override
    public void add(UserDto userDto) throws ServiceException {
        Connection connection = ConnectionUtil.getConnection();
        try {
            User user = DtoToEntityConverter.toUser(userDto);
            connection.setAutoCommit(false);
            userDao.add(user);
            connection.commit();
            BookingSystemLogger.getInstance().logInfo(getClass(), ServiceMessage.TRANSACTION_SUCCEEDED);
        } catch (SQLException | DaoException e) {
            ExceptionHandler.handleSQLOrDaoException(connection, e, getClass());
        }
    }

    @Override
    public UserDto getById(long id) throws ServiceException {
        Connection connection = ConnectionUtil.getConnection();
        UserDto userDto = null;
        try {
            connection.setAutoCommit(false);
            User user = userDao.getById(id);
            userDto = DtoToEntityConverter.toUserDto(user);
            connection.commit();
            BookingSystemLogger.getInstance().logInfo(getClass(), ServiceMessage.TRANSACTION_SUCCEEDED);
        } catch (SQLException | DaoException e) {
            ExceptionHandler.handleSQLOrDaoException(connection, e, getClass());
        }
        return userDto;
    }

    @Override
    public List<UserDto> getAll() throws ServiceException {
        Connection connection = ConnectionUtil.getConnection();
        List<UserDto> userDtoList = null;
        try {
            connection.setAutoCommit(false);
            List<User> userList = userDao.getAll();
            userDtoList = DtoToEntityConverter.toUserDtoList(userList);
            connection.commit();
            BookingSystemLogger.getInstance().logInfo(getClass(), ServiceMessage.TRANSACTION_SUCCEEDED);
        } catch (SQLException | DaoException e) {
            ExceptionHandler.handleSQLOrDaoException(connection, e, getClass());
        }
        return userDtoList;
    }



    @Override
    public void update(UserDto userDto) throws ServiceException {
        Connection connection = ConnectionUtil.getConnection();
        try {
            connection.setAutoCommit(false);
            User user = DtoToEntityConverter.toUser(userDto);
            userDao.update(user);
            connection.commit();
            BookingSystemLogger.getInstance().logInfo(getClass(), ServiceMessage.TRANSACTION_SUCCEEDED);
        } catch (SQLException | DaoException e) {
            ExceptionHandler.handleSQLOrDaoException(connection, e, getClass());
        }
    }

    @Override
    public void delete(long id) throws ServiceException {

    }

    public boolean checkAuthorization(String login, String password) throws ServiceException {
        Connection connection = ConnectionUtil.getConnection();
        boolean isAuthorized = false;
        try {
            connection.setAutoCommit(false);
            isAuthorized = userDao.isAuthorized(login, password);
            connection.commit();
            BookingSystemLogger.getInstance().logInfo(getClass(), ServiceMessage.TRANSACTION_SUCCEEDED);
        } catch (SQLException | DaoException e) {
            ExceptionHandler.handleSQLOrDaoException(connection, e, getClass());
        }
        return isAuthorized;
    }

    public UserDto getByLogin(String login) throws ServiceException {
        Connection connection = ConnectionUtil.getConnection();
        UserDto userDto = null;
        try {
            connection.setAutoCommit(false);
            User user = userDao.getByLogin(login);
            userDto = DtoToEntityConverter.toUserDto(user);
            connection.commit();
            BookingSystemLogger.getInstance().logInfo(getClass(), ServiceMessage.TRANSACTION_SUCCEEDED);
        } catch (SQLException | DaoException e) {
            ExceptionHandler.handleSQLOrDaoException(connection, e, getClass());
        }
        return userDto;
    }

    public UserDto getByEmail(String email) throws ServiceException {
        Connection connection = ConnectionUtil.getConnection();
        UserDto userDto = null;
        try {
            connection.setAutoCommit(false);
            User user = userDao.getByEmail(email);
            userDto = DtoToEntityConverter.toUserDto(user);
            connection.commit();
            BookingSystemLogger.getInstance().logInfo(getClass(), ServiceMessage.TRANSACTION_SUCCEEDED);
        } catch (SQLException | DaoException e) {
            ExceptionHandler.handleSQLOrDaoException(connection, e, getClass());
        }
        return userDto;
    }

    public boolean checkIsNewUser(UserDto userDto) throws ServiceException {
        Connection connection = ConnectionUtil.getConnection();
        boolean isNew = false;
        try {
            connection.setAutoCommit(false);
            User user = DtoToEntityConverter.toUser(userDto);
            isNew = userDao.isNewUser(user);
            connection.commit();
            BookingSystemLogger.getInstance().logInfo(getClass(), ServiceMessage.TRANSACTION_SUCCEEDED);
        } catch (SQLException | DaoException e) {
            ExceptionHandler.handleSQLOrDaoException(connection, e, getClass());
        }
        return isNew;
    }

    public void registrateUser(UserDto userDto) throws ServiceException {
        Connection connection = ConnectionUtil.getConnection();
        try {
            connection.setAutoCommit(false);
            User user = DtoToEntityConverter.toUser(userDto);
            userDao.add(user);
            connection.commit();
            BookingSystemLogger.getInstance().logInfo(getClass(), ServiceMessage.TRANSACTION_SUCCEEDED);
        } catch (SQLException | DaoException e) {
            ExceptionHandler.handleSQLOrDaoException(connection, e, getClass());
        }
    }
}
