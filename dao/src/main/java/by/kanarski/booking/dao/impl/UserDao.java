package by.kanarski.booking.dao.impl;

import by.kanarski.booking.constants.ColumnName;
import by.kanarski.booking.constants.DaoMessages;
import by.kanarski.booking.dao.interfaces.IUserDao;
import by.kanarski.booking.entities.User;
import by.kanarski.booking.exceptions.DaoException;
import by.kanarski.booking.utils.BookingSystemLogger;
import by.kanarski.booking.utils.ClosingUtil;
import by.kanarski.booking.utils.ConnectionUtil;
import by.kanarski.booking.utils.EntityParser;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Dzmitry Kanarski
 * @version 1.0
 * @see IUserDao
 */

public class UserDao implements IUserDao {

    private static UserDao instance = null;
    private static Connection connection;

    /**
     * SQL queries
     */

    private final String ADD_QUERY = "INSERT INTO USERS (FIRST_NAME, LAST_NAME, EMAIL, LOGIN, PASSWORD, ROLE, USER_STATUS)" +
            " VALUES(?, ?, ?, ?, ?, ?, ?)";
    private final String GET_BY_ID_QUERY = "SELECT * FROM USERS WHERE USER_ID = ?";
    private final String GET_BY_LOGIN_QUERY = "SELECT * FROM USERS WHERE LOGIN = ?";
    private final String GET_BY_EMAIL_QUERY = "SELECT * FROM USERS WHERE EMAIL = ?";
    private final String GET_ALL_QUERY = "SELECT * FROM USERS WHERE USER_STATUS = 'active'";
    private final String UPDATE_QUERY = "UPDATE USERS SET FIRST_NAME = ?, LAST_NAME = ?, " +
            "EMAIL = ?, LOGIN = ?, PASSWORD = ?, USER_STATUS = ? WHERE USER_ID = ?";
    private final String DELETE_QUERY = "DELETE FROM USERS WHERE USER_ID = ?";
    private final String CHECK_AUTHORIZATION_QUERY = "SELECT LOGIN, PASSWORD FROM USERS WHERE LOGIN = ? AND PASSWORD = ?";
    private final String CHECK_LOGIN_QUERY = "SELECT LOGIN FROM USERS WHERE LOGIN = ?";

    private UserDao() {
    }

    public static UserDao getInstance() {
        if (instance == null) {
            instance = new UserDao();
            connection = ConnectionUtil.getConnection();
        }
        return instance;
    }

    public static UserDao getTestInstance() {
        if (instance == null) {
            instance = new UserDao();
            connection = ConnectionUtil.getTestConnection();
        }
        return instance;
    }

    @Override
    public User add(User user) throws DaoException {
//        Connection connection = ConnectionUtil.getConnection();
        ResultSet resultSet = null;
        try (PreparedStatement stm = connection.prepareStatement(ADD_QUERY,
                Statement.RETURN_GENERATED_KEYS)) {
            stm.setString(1, user.getFirstName());
            stm.setString(2, user.getLastName());
            stm.setString(3, user.getEmail());
            stm.setString(4, user.getLogin());
            stm.setString(5, user.getPassword());
            stm.setString(6, user.getRole());
            stm.setString(7, user.getUserStatus());
            stm.executeUpdate();
            resultSet = stm.getGeneratedKeys();
            resultSet.next();
            user.setUserId(resultSet.getLong(1));
        } catch (SQLException e) {
            BookingSystemLogger.getInstance().logError(getClass(), DaoMessages.ADD_USER_EXCEPTION);
            throw new DaoException(DaoMessages.ADD_USER_EXCEPTION, e);
        } finally {
            ClosingUtil.close(resultSet);
        }
        return user;
    }

    @Override
    public User getById(long id) throws DaoException {
        User user = null;
//        Connection connection = ConnectionUtil.getConnection();
        ResultSet resultSet = null;
        try (PreparedStatement stm = connection.prepareStatement(GET_BY_ID_QUERY)) {
            stm.setLong(1, id);
            resultSet = stm.executeQuery();
            resultSet.next();
            user = EntityParser.parseUser(resultSet);
        } catch (SQLException e) {
            BookingSystemLogger.getInstance().logError(getClass(), DaoMessages.GET_USER_EXCEPTION);
            throw new DaoException(DaoMessages.GET_USER_EXCEPTION, e);
        } finally {
            ClosingUtil.close(resultSet);
        }
        return user;
    }

    @Override
    public List<User> getAll() throws DaoException {
        List<User> list = new ArrayList<>();
//        Connection connection = ConnectionUtil.getConnection();
        ResultSet resultSet = null;
        try (PreparedStatement stm = connection.prepareStatement(GET_ALL_QUERY)) {
            resultSet = stm.executeQuery();
            while (resultSet.next()) {
                User user = EntityParser.parseUser(resultSet);
                list.add(user);
            }
        } catch (SQLException e) {
            BookingSystemLogger.getInstance().logError(getClass(), DaoMessages.GET_USERS_EXCEPTION);
            throw new DaoException(DaoMessages.GET_USERS_EXCEPTION, e);
        } finally {
            ClosingUtil.close(resultSet);
        }
        return list;
    }

    @Override
    public void update(User user) throws DaoException {
//        Connection connection = ConnectionUtil.getConnection();
        try (PreparedStatement stm = connection.prepareStatement(UPDATE_QUERY)) {
            stm.setString(1, user.getFirstName());
            stm.setString(2, user.getLastName());
            stm.setString(3, user.getEmail());
            stm.setString(4, user.getLogin());
            stm.setString(5, user.getPassword());
            stm.setString(6, user.getUserStatus());
            stm.setLong(7, user.getUserId());
            stm.executeUpdate();
        } catch (SQLException e) {
            BookingSystemLogger.getInstance().logError(getClass(), DaoMessages.UPDATE_USER_EXCEPTION);
            throw new DaoException(DaoMessages.UPDATE_USER_EXCEPTION, e);
        }
    }

    @Override
    public void delete(User user) throws DaoException {
//        Connection connection = ConnectionUtil.getConnection();
        try (PreparedStatement stm = connection.prepareStatement(DELETE_QUERY)) {
            stm.setLong(1, user.getUserId());
            stm.executeUpdate();
        } catch (SQLException e) {
            BookingSystemLogger.getInstance().logError(getClass(), DaoMessages.DELETE_USER_EXCEPTION);
            throw new DaoException(DaoMessages.DELETE_USER_EXCEPTION, e);
        }
    }

    /**
     * Recives <b>{@link User}</b> from databse by login
     * @param login the user login
     * @return an entity with the corresponding id
     * @throws DaoException
     */

    public User getByLogin(String login) throws DaoException {
        User user = null;
//        Connection connection = ConnectionUtil.getConnection();
        ResultSet resultSet = null;
        try (PreparedStatement stm = connection.prepareStatement(GET_BY_LOGIN_QUERY)) {
            stm.setString(1, login);
            resultSet = stm.executeQuery();
            resultSet.next();
            user = EntityParser.parseUser(resultSet);
        } catch (SQLException e) {
            BookingSystemLogger.getInstance().logError(getClass(), DaoMessages.GET_USER_EXCEPTION);
            throw new DaoException(DaoMessages.GET_USER_EXCEPTION, e);
        } finally {
            ClosingUtil.close(resultSet);
        }
        return user;
    }

    public User getByEmail(String email) throws DaoException {
        User user = null;
//        Connection connection = ConnectionUtil.getConnection();
        ResultSet resultSet = null;
        try (PreparedStatement stm = connection.prepareStatement(GET_BY_EMAIL_QUERY)) {
            stm.setString(1, email);
            resultSet = stm.executeQuery();
            resultSet.next();
            user = EntityParser.parseUser(resultSet);
        } catch (SQLException e) {
            BookingSystemLogger.getInstance().logError(getClass(), DaoMessages.GET_USER_EXCEPTION);
            throw new DaoException(DaoMessages.GET_USER_EXCEPTION, e);
        } finally {
            ClosingUtil.close(resultSet);
        }
        return user;
    }

    public boolean isAuthorized(String login, String password) throws DaoException {
//        Connection connection = ConnectionUtil.getConnection();
        ResultSet resultSet = null;
        boolean isLogIn = false;
        try (PreparedStatement stm = connection.prepareStatement(CHECK_AUTHORIZATION_QUERY)) {
            stm.setString(1, login);
            stm.setString(2, password);
            resultSet = stm.executeQuery();
            if (resultSet.next()) {
                isLogIn = true;
            }
        } catch (SQLException e) {
            BookingSystemLogger.getInstance().logError(getClass(), DaoMessages.CHECK_USER_AUTHORIZATION_EXCEPTION);
            throw new DaoException(DaoMessages.CHECK_USER_AUTHORIZATION_EXCEPTION, e);
        } finally {
            ClosingUtil.close(resultSet);
        }
        return isLogIn;
    }

    public boolean isNewUser(String login) throws DaoException {
//        Connection connection = ConnectionUtil.getConnection();
        ResultSet resultSet = null;
        boolean isNew = true;
        try (PreparedStatement stm = connection.prepareStatement(CHECK_LOGIN_QUERY)) {
            stm.setString(1, login);
            resultSet = stm.executeQuery();
            if (resultSet.next()) {
                isNew = false;
            }
        } catch (SQLException e) {
            BookingSystemLogger.getInstance().logError(getClass(), DaoMessages.CHECK_IS_NEW_USER_EXCEPTION);
            throw new DaoException(DaoMessages.CHECK_IS_NEW_USER_EXCEPTION, e);
        } finally {
            ClosingUtil.close(resultSet);
        }
        return isNew;
    }
}
