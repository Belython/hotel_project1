package by.kanarski.booking.utils;

import by.kanarski.booking.constants.ColumnName;
import by.kanarski.booking.entities.*;

import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

/**
 * Represents static methods for parsing entities
 * based on <code>ResultSet</code>
 * @author Dzmitry Kanarski
 * @version 1.0
 */

public class EntityParser {
    private EntityParser() {
    }

    /**
     * Recives User
     * @param rs
     * @return
     * @throws SQLException
     */

    public static User parseUser(ResultSet rs) throws SQLException {
        long userId = rs.getLong(ColumnName.USER_ID);
        String userFirstName = rs.getString(ColumnName.USER_FIRST_NAME);
        String userLastName = rs.getString(ColumnName.USER_LAST_NAME);
        String userEmail = rs.getString(ColumnName.USER_EMAIL);
        String userLogin = rs.getString(ColumnName.USER_LOGIN);
        String userPassword = rs.getString(ColumnName.USER_PASSWORD);
        String userRole = rs.getString(ColumnName.USER_ROLE);
        String userStatus = rs.getString(ColumnName.USER_STATUS);
        User user = EntityBuilder.buildUser(userId, userFirstName, userLastName, userEmail, userLogin, userPassword,
                userRole, userStatus);
        return user;
    }

    public static Hotel parseHotel(ResultSet rs) throws SQLException {
        long hotelId = rs.getLong(ColumnName.HOTEL_ID);
        Location hotelLocation = parseLocation(rs);
        String hotelName = rs.getString(ColumnName.HOTEL_NAME);
        String hotelStatus = rs.getString(ColumnName.HOTEL_STATUS);
        Hotel hotel = EntityBuilder.buildHotel(hotelId, hotelLocation, hotelName, hotelStatus);
        return hotel;
    }

    public static RoomType parseRoomType(ResultSet rs) throws SQLException {
        long roomTypeId = rs.getLong(ColumnName.ROOM_TYPE_ID);
        String roomTypeName = rs.getString(ColumnName.ROOM_TYPE_NAME);
        int maxPersons = rs.getInt(ColumnName.ROOM_TYPE_MAX_PERSONS);
        double roomPricePerNight = rs.getDouble(ColumnName.ROOM_TYPE_PRICE_PER_NIGHT);
        Set<String> facilities = null;
        Blob serializedFacilities = rs.getBlob(ColumnName.ROOM_TYPE_FACILITIES);
        facilities = SerializationUtil.deserialize(serializedFacilities, facilities);
        String roomTypeStatus = rs.getString(ColumnName.ROOM_TYPE_STATUS);
        RoomType roomType = EntityBuilder.buildRoomType(roomTypeId, roomTypeName, maxPersons, roomPricePerNight,
                facilities, roomTypeStatus);
        return roomType;
    }

    public static Room parseRoom(ResultSet rs) throws SQLException {
        long roomId = rs.getLong(ColumnName.ROOM_ID);
        Hotel hotel = parseHotel(rs);
        RoomType roomType = parseRoomType(rs);
        int roomNumber = rs.getInt(ColumnName.ROOM_NUMBER);
        TreeMap<Long, Long> bookedDates = null;
        Blob serializedBookedDates = rs.getBlob(ColumnName.ROOM_BOOKED_DATES);
        bookedDates = SerializationUtil.deserialize(serializedBookedDates, bookedDates);
        String roomStatus = rs.getString(ColumnName.ROOM_STATUS);
        Room room = EntityBuilder.buildRoom(roomId, hotel, roomType, roomNumber, bookedDates, roomStatus);
        return room;
    }

    public static Bill parseBill(ResultSet rs) throws SQLException {
        long billId = rs.getLong(ColumnName.BILL_ID);
        User user = parseUser(rs);
        int totalPersons = rs.getInt(ColumnName.BILL_TOTAL_PERSONS);
        long checkInDate = rs.getLong(ColumnName.BILL_CHECK_IN_DATE);
        long checkOutDate = rs.getLong(ColumnName.BILL_CHECK_OUT_DATE);
        List<Long> roomIdList = null;
        roomIdList = SerializationUtil.deserialize(rs.getBlob(ColumnName.BILL_ROOM_ID_LIST), roomIdList);
        double paymenAmount = rs.getDouble(ColumnName.BILL_PAYMENT_AMOUNT);
        String billStatus = rs.getString(ColumnName.BILL_STATUS);
        Bill bill = EntityBuilder.buildBill(billId, user, totalPersons, checkInDate, checkOutDate, roomIdList,
                paymenAmount, billStatus);
        return bill;
    }

    public static Location parseLocation(ResultSet rs) throws SQLException {
        long id = rs.getLong(ColumnName.LOCATION_ID);
        String country = rs.getString(ColumnName.LOCATION_COUNTRY);
        String city = rs.getString(ColumnName.LOCATION_CITY);
        String status = rs.getString(ColumnName.LOCATION_STATUS);
        Location location = EntityBuilder.buildLocation(id, country, city, status);
        return location;
    }

}
