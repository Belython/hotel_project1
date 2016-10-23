package by.kanarski.booking.dao.impl;

import by.kanarski.booking.constants.DaoMessage;
import by.kanarski.booking.dao.interfaces.IRoomDao;
import by.kanarski.booking.dto.OrderDto;
import by.kanarski.booking.entities.Room;
import by.kanarski.booking.entities.RoomType;
import by.kanarski.booking.exceptions.DaoException;
import by.kanarski.booking.utils.*;
import by.kanarski.booking.utils.ConnectionUtil;

import java.sql.*;
import java.util.*;

public class RoomDao implements IRoomDao {

    private static RoomDao instance = null;

    private final String ADD_QUERY = "INSERT INTO ROOMS (" +
            "HOTEL_ID, ROOM_TYPE_ID, ROOM_NUMBER, BOOKED_DATES, ROOM_STATUS) " +
            "VALUES(?, ?, ?, ?, ?)";
    private final String GET_BY_ID_QUERY = "SELECT R.*, RT.*, H.*, L.* " +
            "FROM ROOMS R " +
            "JOIN ROOMS_TYPES RT ON R.ROOM_TYPE_ID = RT.ROOM_TYPE_ID " +
            "JOIN HOTELS H ON R.HOTEL_ID = H.HOTEL_ID " +
            "JOIN LOCATIONS L ON H.LOCATION_ID = L.LOCATION_ID " +
            "WHERE R.ROOM_ID = ?";
    private final String GET_AVAILABLE_ROOMS_QUERY = "SELECT R.*, RT.*, H.*, L.* " +
            "FROM ROOMS R " +
            "JOIN ROOMS_TYPES RT ON R.ROOM_TYPE_ID = RT.ROOM_TYPE_ID " +
            "JOIN HOTELS H ON R.HOTEL_ID = H.HOTEL_ID " +
            "JOIN LOCATIONS L ON H.LOCATION_ID = L.LOCATION_ID " +
            "WHERE L.COUNTRY = ? AND L.CITY = ? AND (? = H.HOTEL_NAME OR ? = 'allHotels') AND RT.MAX_PERSONS >= ? " +
            "ORDER BY H.HOTEL_NAME ASC";
    private final String GET_BY_HOTEL_ID_QUERY = "SELECT R.*, RT.*, H.*, L.* " +
            "FROM ROOMS R " +
            "JOIN ROOMS_TYPES RT ON R.ROOM_TYPE_ID = RT.ROOM_TYPE_ID " +
            "JOIN HOTELS H ON R.HOTEL_ID = H.HOTEL_ID " +
            "JOIN LOCATIONS L ON H.LOCATION_ID = L.LOCATION_ID " +
            "WHERE H.HOTEL_ID = ? " +
            "ORDER BY RT.ROOM_TYPE ASC";
    private final String GET_BY_ORDER_QUERY = "SELECT R.*, RT.*, H.*, L.* " +
            "FROM ROOMS R " +
            "JOIN ROOMS_TYPES RT ON R.ROOM_TYPE_ID = RT.ROOM_TYPE_ID " +
            "JOIN HOTELS H ON R.HOTEL_ID = H.HOTEL_ID " +
            "JOIN LOCATIONS L ON H.LOCATION_ID = L.LOCATION_ID " +
            "WHERE L.COUNTRY = ? AND L.CITY = ? AND H.HOTEL_NAME = ? AND RT.ROOM_TYPE_ID = ? AND (R.BOOKING_END_DATE < ? OR R.BOOKING_START_DATE > ?) " +
            "ORDER BY R.ROOM_NUMBER ASC";
    private final String UPDATE_QUERY = "UPDATE ROOMS SET HOTEL_ID = ?, ROOM_TYPE_ID = ?, ROOM_NUMBER = ?," +
            " BOOKED_DATES = ?, ROOM_STATUS = ? WHERE ROOM_ID = ?";
    private final String GET_BY_LIST_ID_QUERY = "SELECT R.*, RT.*, H.*, L.* " +
            "FROM ROOMS R " +
            "JOIN ROOMS_TYPES RT ON R.ROOM_TYPE_ID = RT.ROOM_TYPE_ID " +
            "JOIN HOTELS H ON R.HOTEL_ID = H.HOTEL_ID " +
            "JOIN LOCATIONS L ON H.LOCATION_ID = L.LOCATION_ID " +
            "WHERE R.ROOM_ID IN (parameters)";
    private final String DELETE_QUERY = "UPDATE ORDERS SET STATUS = 'deleted' WHERE ID = ?";
    private final String GET_ALL = "SELECT R.*, RT.*, H.*, L.* " +
            "FROM ROOMS R " +
            "JOIN ROOMS_TYPES RT ON R.ROOM_TYPE_ID = RT.ROOM_TYPE_ID " +
            "JOIN HOTELS H ON R.HOTEL_ID = H.HOTEL_ID " +
            "JOIN LOCATIONS L ON H.LOCATION_ID = L.LOCATION_ID";
    private final String RESERVE_ROOM_QUERY = "UPDATE ROOMS SET BOOKED_DATES = ? WHERE ROOM_ID = ?";

    private RoomDao() {
    }

    public static RoomDao getInstance() {
        if (instance == null) {
            instance = new RoomDao();
        }
        return instance;
    }

    @Override
    public Room add(Room room) throws DaoException {
        Connection connection = ConnectionUtil.getConnection();
        ResultSet resultSet = null;
        try (PreparedStatement stm = connection.prepareStatement(ADD_QUERY,
                Statement.RETURN_GENERATED_KEYS)) {
            stm.setLong(1, room.getHotel().getHotelId());
            stm.setLong(2, room.getRoomType().getRoomTypeId());
            stm.setInt(3, room.getRoomNumber());
            Blob serializedBookedDates = SerializationUtil.serialize(room.getBookedDates());
            stm.setBlob(4, serializedBookedDates);
            stm.setString(5, room.getRoomStatus());
            stm.executeUpdate();
            resultSet = stm.getGeneratedKeys();
            resultSet.next();
            room.setRoomId(resultSet.getLong(1));
        } catch (SQLException e) {
            BookingSystemLogger.getInstance().logError(getClass(), DaoMessage.ADD_ROOM_EXCEPTION);
            throw new DaoException(DaoMessage.ADD_ROOM_EXCEPTION, e);
        } finally {
            ClosingUtil.close(resultSet);
        }
        return room;
    }

    @Override
    public Room getById(long id) throws DaoException {
        Room room = null;
        Connection connection = ConnectionUtil.getConnection();
        try (PreparedStatement stm = connection.prepareStatement(GET_BY_ID_QUERY)) {
            stm.setLong(1, id);
            ResultSet resultSet = stm.executeQuery();
            resultSet.next();
            room = EntityParser.parseRoom(resultSet);
        } catch (SQLException e) {
            BookingSystemLogger.getInstance().logError(getClass(), DaoMessage.GET_ROOM_EXCEPTION);
            throw new DaoException(DaoMessage.GET_ROOM_EXCEPTION, e);
        }
        return room;
    }

    @Override
    public List<Room> getAll() throws DaoException {
        List<Room> roomList = new ArrayList<>();
        Connection connection = ConnectionUtil.getConnection();
        try (PreparedStatement stm = connection.prepareStatement(GET_ALL)) {
            ResultSet resultSet = stm.executeQuery();
            while (resultSet.next()) {
                roomList.add(EntityParser.parseRoom(resultSet));
            }
        } catch (SQLException e) {
            BookingSystemLogger.getInstance().logError(getClass(), DaoMessage.GET_ROOM_EXCEPTION);
            throw new DaoException(DaoMessage.GET_ROOM_EXCEPTION, e);
        }
        return roomList;
    }

    @Override
    public void update(Room room) throws DaoException {
        Connection connection = ConnectionUtil.getConnection();
        try (PreparedStatement stm = connection.prepareStatement(UPDATE_QUERY)) {
            stm.setLong(1, room.getHotel().getHotelId());
            stm.setLong(2, room.getRoomType().getRoomTypeId());
            stm.setInt(3, room.getRoomNumber());
            Blob serializedBookedDates = SerializationUtil.serialize(room.getBookedDates());
            stm.setBlob(4, serializedBookedDates);
            stm.setString(5, room.getRoomStatus());
            stm.setLong(6, room.getRoomId());
            stm.executeUpdate();
        } catch (SQLException e) {
            BookingSystemLogger.getInstance().logError(getClass(), DaoMessage.UPDATE_ROOM_EXCEPTION);
            throw new DaoException(DaoMessage.UPDATE_ROOM_EXCEPTION, e);
        }
    }

    @Override
    public void delete(Room room) throws DaoException {

    }

    public List<Room> getAvailableRooms(OrderDto orderDto) throws DaoException {
        List<Room> rooms = new ArrayList<>();
        Connection connection = ConnectionUtil.getConnection();
        try (PreparedStatement stm = connection.prepareStatement(GET_AVAILABLE_ROOMS_QUERY)) {
            stm.setString(1, orderDto.getHotel().getLocation().getCountry());
            stm.setString(2, orderDto.getHotel().getLocation().getCity());
            stm.setString(3, orderDto.getHotel().getHotelName());
            stm.setString(4, orderDto.getHotel().getHotelName());
            stm.setInt(5, orderDto.getTotalPersons());
            ResultSet resultSet = stm.executeQuery();
            while (resultSet.next()) {
                rooms.add(EntityParser.parseRoom(resultSet));
            }
        } catch (SQLException e) {
            BookingSystemLogger.getInstance().logError(getClass(), DaoMessage.GET_ROOM_EXCEPTION);
            throw new DaoException(DaoMessage.GET_ROOM_EXCEPTION, e);
        }
        return rooms;
    }

    public List<Room> getByHotelId(long hotelId) throws DaoException {
        List<Room> rooms = new ArrayList<>();
        Connection connection = ConnectionUtil.getConnection();
        try (PreparedStatement stm = connection.prepareStatement(GET_BY_HOTEL_ID_QUERY)) {
            stm.setLong(1, hotelId);
            ResultSet resultSet = stm.executeQuery();
            while (resultSet.next()) {
                rooms.add(EntityParser.parseRoom(resultSet));
            }
        } catch (SQLException e) {
            BookingSystemLogger.getInstance().logError(getClass(), DaoMessage.GET_ROOM_EXCEPTION);
            throw new DaoException(DaoMessage.GET_ROOM_EXCEPTION, e);
        }
        return rooms;
    }

    public List<Room> getByIdList(List<Long> idList) throws DaoException {
        List<Room> roomList = new ArrayList<>();
        Connection connection = ConnectionUtil.getConnection();
        final String GET_BY_LIST_ID_FINISHED_QUERY = getFinishedQuery(GET_BY_LIST_ID_QUERY, idList);
        try (PreparedStatement stm = connection.prepareStatement(GET_BY_LIST_ID_FINISHED_QUERY)) {
            ResultSet resultSet = stm.executeQuery();
            while (resultSet.next()) {
                Room room = EntityParser.parseRoom(resultSet);
                roomList.add(room);
            }
        } catch (SQLException e) {
            BookingSystemLogger.getInstance().logError(getClass(), DaoMessage.GET_ROOM_EXCEPTION);
            throw new DaoException(DaoMessage.GET_ROOM_EXCEPTION, e);
        }
        return roomList;
    }

    public void updateList(List<Room> roomList) throws DaoException {
        Connection connection = ConnectionUtil.getConnection();
        try (PreparedStatement stm = connection.prepareStatement(UPDATE_QUERY)) {
            for (Room room : roomList) {
                stm.setLong(1, room.getHotel().getHotelId());
                stm.setLong(2, room.getRoomType().getRoomTypeId());
                stm.setInt(3, room.getRoomNumber());
                Blob serializedBookedDates = SerializationUtil.serialize(room.getBookedDates());
                stm.setBlob(4, serializedBookedDates);
                stm.setString(5, room.getRoomStatus());
                stm.setLong(6, room.getRoomId());
                stm.addBatch();
            }
            stm.executeBatch();
        } catch (SQLException e) {
            BookingSystemLogger.getInstance().logError(getClass(), DaoMessage.UPDATE_ROOM_EXCEPTION);
            throw new DaoException(DaoMessage.UPDATE_ROOM_EXCEPTION, e);
        }
    }

    public void addList(List<Room> roomList) throws DaoException {
        Connection connection = ConnectionUtil.getConnection();
        try (PreparedStatement stm = connection.prepareStatement(ADD_QUERY,
                Statement.RETURN_GENERATED_KEYS)) {
            for (Room room : roomList) {
                stm.setLong(1, room.getHotel().getHotelId());
                stm.setLong(2, room.getRoomType().getRoomTypeId());
                stm.setInt(3, room.getRoomNumber());
                Blob serializedBookedDates = SerializationUtil.serialize(room.getBookedDates());
                stm.setBlob(4, serializedBookedDates);
                stm.setString(5, room.getRoomStatus());
                stm.addBatch();
            }
            stm.executeBatch();
        } catch (SQLException e) {
            BookingSystemLogger.getInstance().logError(getClass(), DaoMessage.ADD_ROOM_EXCEPTION);
            throw new DaoException(DaoMessage.ADD_ROOM_EXCEPTION, e);
        }
    }

    private String getFinishedQuery(String initialQuery, List<Long> parametersList) {
        StringBuilder buffer = new StringBuilder();
        String regex = "parameters";
        Iterator<Long> iterator = parametersList.listIterator();
        buffer.append(iterator.next());
        while (iterator.hasNext()) {
            buffer.append("," + iterator.next());
        }
        String finishedQuery = initialQuery.replace(regex, buffer.toString());
        return finishedQuery;
    }

    public void reserveRoomList(List<Room> roomList) throws DaoException {
        Connection connection = ConnectionUtil.getConnection();
        try (PreparedStatement stm = connection.prepareStatement(RESERVE_ROOM_QUERY)) {
            for (Room room : roomList) {
                Blob serializedBookedDates = SerializationUtil.serialize(room.getBookedDates());
                stm.setBlob(1, serializedBookedDates);
                stm.setLong(2, room.getRoomId());
                stm.addBatch();
            }
            stm.executeBatch();
        } catch (SQLException e) {
            BookingSystemLogger.getInstance().logError(getClass(), DaoMessage.UPDATE_ROOM_EXCEPTION);
            throw new DaoException(DaoMessage.UPDATE_ROOM_EXCEPTION, e);
        }
    }

    public static void main(String[] args) throws DaoException {
        Set<String> fac = new HashSet<>();
        fac.add("safe");
        fac.add("wi-fi");
        fac.add("profit");
        fac.add("toilet");
        fac.add("basik");
        RoomType rt = EntityBuilder.buildRoomType("Family", 3, 500, fac, "active");
        RoomTypeDao.getInstance().add(rt);
    }

}
