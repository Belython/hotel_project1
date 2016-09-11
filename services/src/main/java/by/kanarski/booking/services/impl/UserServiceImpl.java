package by.kanarski.booking.services.impl;

import by.kanarski.booking.constants.ServiceMessages;
import by.kanarski.booking.dao.impl.UserDao;
import by.kanarski.booking.entities.User;
import by.kanarski.booking.exceptions.DaoException;
import by.kanarski.booking.exceptions.ServiceException;
import by.kanarski.booking.services.interfaces.IUserService;
import by.kanarski.booking.utils.BookingSystemLogger;
import by.kanarski.booking.utils.ConnectionUtil;
import by.kanarski.booking.utils.ExceptionHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements IUserService {
    private static UserServiceImpl instance;

    private UserServiceImpl() {
    }

    public static synchronized UserServiceImpl getInstance() {
        if (instance == null) {
            instance = new UserServiceImpl();
        }
        return instance;
    }

    @Override
    public void add(User user) throws ServiceException {
        Connection connection = ConnectionUtil.getConnection();
        try {
            connection.setAutoCommit(false);
            UserDao.getInstance().add(user);
            connection.commit();
            BookingSystemLogger.getInstance().logError(getClass(), ServiceMessages.TRANSACTION_SUCCEEDED);
        } catch (SQLException | DaoException e) {
            ExceptionHandler.handleSQLOrDaoException(connection, e, getClass());
        }
    }

    @Override
    public List<User> getAll() throws ServiceException {
        Connection connection = ConnectionUtil.getConnection();
        List<User> users = null;
        try {
            connection.setAutoCommit(false);
            users = UserDao.getInstance().getAll();
            for (User user : users) {
                users.add(user);
            }
            connection.commit();
            BookingSystemLogger.getInstance().logError(getClass(), ServiceMessages.TRANSACTION_SUCCEEDED);
        } catch (SQLException | DaoException e) {
            ExceptionHandler.handleSQLOrDaoException(connection, e, getClass());
        }
        return users;
    }

    @Override
    public User getById(long id) throws ServiceException {
        Connection connection = ConnectionUtil.getConnection();
        User user = null;
        try {
            connection.setAutoCommit(false);
            user = UserDao.getInstance().getById(id);
            connection.commit();
            BookingSystemLogger.getInstance().logError(getClass(), ServiceMessages.TRANSACTION_SUCCEEDED);
        } catch (SQLException | DaoException e) {
            ExceptionHandler.handleSQLOrDaoException(connection, e, getClass());
        }
        return user;
    }

    @Override
    public void update(User entity) throws ServiceException {

    }

    @Override
    public void delete(long id) throws ServiceException {

    }

    public boolean checkUserAuthorization(String login, String password) throws ServiceException {
        Connection connection = ConnectionUtil.getConnection();
        boolean isAuthorized = false;
        try {
            connection.setAutoCommit(false);
            isAuthorized = UserDao.getInstance().isAuthorized(login, password);
            connection.commit();
            BookingSystemLogger.getInstance().logError(getClass(), ServiceMessages.TRANSACTION_SUCCEEDED);
        } catch (SQLException | DaoException e) {
            ExceptionHandler.handleSQLOrDaoException(connection, e, getClass());
        }
        return isAuthorized;
    }

    public User getUserByLogin(String login) throws ServiceException {
        Connection connection = ConnectionUtil.getConnection();
        User user = null;
        try {
            connection.setAutoCommit(false);
            user = UserDao.getInstance().getByLogin(login);
            connection.commit();
            BookingSystemLogger.getInstance().logError(getClass(), ServiceMessages.TRANSACTION_SUCCEEDED);
        } catch (SQLException | DaoException e) {
            ExceptionHandler.handleSQLOrDaoException(connection, e, getClass());
        }
        return user;
    }

    public boolean checkIsNewUser(User user) throws ServiceException {
        Connection connection = ConnectionUtil.getConnection();
        boolean isNew = false;
        try {
            connection.setAutoCommit(false);
            if (UserDao.getInstance().isNewUser(user.getLogin())) {
                isNew = true;
            }
            connection.commit();
            BookingSystemLogger.getInstance().logError(getClass(), ServiceMessages.TRANSACTION_SUCCEEDED);
        } catch (SQLException | DaoException e) {
            ExceptionHandler.handleSQLOrDaoException(connection, e, getClass());
        }
        return isNew;
    }

    public void registrateUser(User user) throws ServiceException {
        Connection connection = ConnectionUtil.getConnection();
        try {
            connection.setAutoCommit(false);
            user.setRole("client");
            user.setStatus("active");
            UserDao.getInstance().add(user);
            connection.commit();
            BookingSystemLogger.getInstance().logError(getClass(), ServiceMessages.TRANSACTION_SUCCEEDED);
        } catch (SQLException | DaoException e) {
            ExceptionHandler.handleSQLOrDaoException(connection, e, getClass());
        }
    }
}
