package by.kanarski.booking.utils;


import by.kanarski.booking.constants.ColumnName;
import by.kanarski.booking.entities.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class EntityParser {
    private EntityParser() {
    }

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
        String hotelCountry = rs.getString(ColumnName.LOCATION_COUNTRY);
        String hotelCity = rs.getString(ColumnName.LOCATION_CITY);
        String hotelName = rs.getString(ColumnName.HOTEL_NAME);
        String hotelStatus = rs.getString(ColumnName.HOTEL_STATUS);
        Hotel hotel = EntityBuilder.buildHotel(hotelId, hotelCountry, hotelCity, hotelName, hotelStatus);
        return hotel;
    }

    public static RoomType parseRoomType(ResultSet rs) throws SQLException {
        long roomTypeId = rs.getLong(ColumnName.ROOM_TYPE_ID);
        String roomTypeName = rs.getString(ColumnName.ROOM_TYPE_NAME);
        int maxPersons = rs.getInt(ColumnName.ROOM_TYPE_MAX_PERSONS);
        int roomPricePerNight = rs.getInt(ColumnName.ROOM_TYPE_PRICE_PER_NIGHT);
        List<String> facilities = null;
        facilities = SerializationUtil.deserialize(rs.getBlob(ColumnName.ROOM_TYPE_FACILITIES), facilities);
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
        long bookingStartDate = rs.getLong(ColumnName.ROOM_BOOKING_START_DATE);
        long bookingEndDate = rs.getLong(ColumnName.ROOM_BOOKING_END_DATE);
        String roomStatus = rs.getString(ColumnName.ROOM_STATUS);
        Room room = EntityBuilder.buildRoom(roomId, hotel, roomType, roomNumber, bookingStartDate, bookingEndDate,
                roomStatus);
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
        int paymenAmount = rs.getInt(ColumnName.BILL_PAYMENT_AMOUNT);
        String billStatus = rs.getString(ColumnName.BILL_STATUS);
        Bill bill = EntityBuilder.buildBill(billId, user, totalPersons, checkInDate, checkOutDate, roomIdList,
                paymenAmount, billStatus);
        return bill;
    }


}
