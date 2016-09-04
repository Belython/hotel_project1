package by.kanarski.booking.dao.impl;

import by.kanarski.booking.constants.DaoMessages;
import by.kanarski.booking.dao.interfaces.IRoomTypeDao;
import by.kanarski.booking.entities.RoomType;
import by.kanarski.booking.exceptions.DaoException;
import by.kanarski.booking.utils.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomTypeDao implements IRoomTypeDao {

    private static RoomTypeDao instance = null;

    private final String ADD_QUERY = "INSERT INTO ROOMS_TYPES (" +
            "ROOM_TYPE, MAX_PERSONS, PRICE_PER_NIGHT, FACILITIES, ROOM_TYPE_STATUS)" +
            "VALUES(?, ?, ?, ?, ?)";
    private final String GET_BY_ID_QUERY = "SELECT * FROM ROOMS_TYPES WHERE ROOM_TYPE_ID = ?";
    private final String GET_ALL_QUERY = "SELECT * FROM ROOMS_TYPES";
    private final String UPDATE_QUERY = "UPDATE ROOMS_TYPES " +
            "SET ROOM_TYPE = ?, MAX_PERSONS = ?, PRICE_PER_NIGHT = ?, FACILITIES = ?, ROOM_TYPE_STATUS = ?" +
            "WHERE ROOM_TYPE_ID = ?";
    private final String DELETE_QUERY = "UPDATE ORDERS SET STATUS = 'deleted' WHERE ID = ?";

    private RoomTypeDao() {
    }

    public static RoomTypeDao getInstance() {
        if (instance == null) {
            instance = new RoomTypeDao();
        }
        return instance;
    }

    @Override
    public RoomType add(RoomType roomType) throws DaoException {
        Connection connection = ConnectionUtil.getConnection();
        ResultSet resultSet = null;
        try (PreparedStatement stm = connection.prepareStatement(ADD_QUERY,
                Statement.RETURN_GENERATED_KEYS)) {
            stm.setString(1, roomType.getName());
            stm.setInt(2, roomType.getMaxPersons());
            stm.setInt(3, roomType.getRoomPricePerNight());
            stm.setBlob(4, SerializationUtil.serialize(roomType.getFacilities()));
            stm.setString(5, roomType.getStatus());
            stm.executeUpdate();
            resultSet = stm.getGeneratedKeys();
            resultSet.next();
            roomType.setId(resultSet.getLong(1));
        } catch (SQLException e) {
            BookingSystemLogger.getInstance().logError(getClass(), DaoMessages.ADD_ROOM_EXCEPTION);
            throw new DaoException(DaoMessages.ADD_ROOM_EXCEPTION, e);
        } finally {
            ClosingUtil.close(resultSet);
        }
        return roomType;
    }

    @Override
    public RoomType getById(long id) throws DaoException {
        RoomType roomType = null;
        Connection connection = ConnectionUtil.getConnection();
        try (PreparedStatement stm = connection.prepareStatement(GET_BY_ID_QUERY)) {
            stm.setLong(1, id);
            ResultSet resultSet = stm.executeQuery();
            resultSet.next();
            roomType = EntityParser.parseRoomType(resultSet);
        } catch (SQLException e) {
            BookingSystemLogger.getInstance().logError(getClass(), DaoMessages.GET_ROOM_EXCEPTION);
            throw new DaoException(DaoMessages.GET_ROOM_EXCEPTION, e);
        }
        return roomType;
    }

    @Override
    public List<RoomType> getAll() throws DaoException {
        List<RoomType> roomTypes = new ArrayList<>();
        Connection connection = ConnectionUtil.getConnection();
        try (PreparedStatement stm = connection.prepareStatement(GET_ALL_QUERY)) {
            ResultSet resultSet = stm.executeQuery();
            while (resultSet.next()) {
                roomTypes.add(EntityParser.parseRoomType(resultSet));
            }
        } catch (SQLException e) {
            BookingSystemLogger.getInstance().logError(getClass(), DaoMessages.GET_ROOM_EXCEPTION);
            throw new DaoException(DaoMessages.GET_ROOM_EXCEPTION, e);
        }
        return roomTypes;
    }

    @Override
    public void update(RoomType roomType) throws DaoException {
        Connection connection = ConnectionUtil.getConnection();
        try (PreparedStatement stm = connection.prepareStatement(UPDATE_QUERY)) {
            stm.setString(1, roomType.getName());
            stm.setInt(2, roomType.getMaxPersons());
            stm.setInt(3, roomType.getRoomPricePerNight());
            stm.setBlob(4, SerializationUtil.serialize(roomType.getFacilities()));
            stm.setString(5, roomType.getStatus());
            stm.setLong(6, roomType.getId());
            stm.executeUpdate();
        } catch (SQLException e) {
            BookingSystemLogger.getInstance().logError(getClass(), DaoMessages.GET_ROOM_EXCEPTION);
            throw new DaoException(DaoMessages.GET_ROOM_EXCEPTION, e);
        }
    }

    @Override
    public void delete(RoomType roomType) throws DaoException {

    }

}
