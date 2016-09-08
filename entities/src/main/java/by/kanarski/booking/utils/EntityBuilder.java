package by.kanarski.booking.utils;


import by.kanarski.booking.constants.Statuses;
import by.kanarski.booking.entities.*;

import java.util.List;

public class EntityBuilder {
    private EntityBuilder() {
    }

    public static User buildUser(long userId, String firstName, String lastName, String email, String login,
                                 String password, String role, String userStatus) {
        User user = buildUser(firstName, lastName, email, login, password, role);
        user.setId(userId);
        user.setStatus(userStatus);
        return user;
    }

    public static User buildUser(String firstName, String lastName, String email, String login,
                                 String password, String role, String userStatus) {
        User user = buildUser(firstName, lastName, email, login, password, role);
        user.setStatus(userStatus);
        return user;
    }

    public static User buildUser(String firstName, String lastName, String email, String login,
                                 String password, String role) {
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setLogin(login);
        user.setPassword(password);
        user.setRole(role);
        return user;
    }

    public static Location buildLocation(long locationId, String country, String city, String locationStatus) {
        Location location = buildLocation(country, city);
        location.setId(locationId);
        location.setStatus(locationStatus);
        return location;
    }

    public static Location buildLocation(String country, String city, String locationStatus) {
        Location location = buildLocation(country, city);
        location.setStatus(locationStatus);
        return location;
    }

    public static Location buildLocation(String country, String city) {
        Location location = new Location();
        location.setCountry(country);
        location.setCity(city);
        return location;
    }

    public static Hotel buildHotel(long hotelId, String hotelCountry, String hotelCity, String hotelName, String hotelStatus) {
        Hotel hotel = buildHotel(hotelCountry, hotelCity, hotelName);
        hotel.setId(hotelId);
        hotel.setStatus(hotelStatus);
        return hotel;
    }

    public static Hotel buildHotel(String hotelCountry, String hotelCity, String hotelName, String hotelStatus) {
        Hotel hotel = buildHotel(hotelCountry, hotelCity, hotelName);
        hotel.setStatus(hotelStatus);
        return hotel;
    }

    public static Hotel buildHotel(String hotelCountry, String hotelCity, String hotelName) {
        Location location = buildLocation(hotelCountry, hotelCity);
        Hotel hotel = new Hotel();
        hotel.setLocation(location);
        hotel.setName(hotelName);
        return hotel;
    }

    public static RoomType buildRoomType(long roomTypeId, String roomTypeName, int maxPersons, int roomPricePerNight,
                                         List<String> facilities, String roomTypeStatus) {
        RoomType roomType = buildRoomType(roomTypeName, maxPersons, roomPricePerNight, facilities);
        roomType.setId(roomTypeId);
        roomType.setName(roomTypeName);
        roomType.setMaxPersons(maxPersons);
        roomType.setRoomPricePerNight(roomPricePerNight);
        roomType.setFacilities(facilities);
        roomType.setStatus(roomTypeStatus);
        return roomType;
    }

    public static RoomType buildRoomType(String roomTypeName, int maxPersons, int roomPricePerNight,
                                         List<String> facilities, String roomTypeStatus) {
        RoomType roomType = buildRoomType(roomTypeName, maxPersons, roomPricePerNight, facilities);
        roomType.setName(roomTypeName);
        roomType.setMaxPersons(maxPersons);
        roomType.setRoomPricePerNight(roomPricePerNight);
        roomType.setFacilities(facilities);
        roomType.setStatus(roomTypeStatus);
        return roomType;
    }

    public static RoomType buildRoomType(String roomTypeName, int maxPersons, int roomPricePerNight,
                                         List<String> facilities) {
        RoomType roomType = new RoomType();
        roomType.setName(roomTypeName);
        roomType.setMaxPersons(maxPersons);
        roomType.setRoomPricePerNight(roomPricePerNight);
        roomType.setFacilities(facilities);
        return roomType;
    }


    public static Room buildRoom(long roomId, Hotel hotel, RoomType roomType, int roomNumber, long bookingStartDate,
                                 long bookingEndDate, String roomStatus) {
        Room room = buildRoom(hotel, roomType, roomNumber, bookingStartDate, bookingEndDate);
        room.setId(roomId);
        room.setStatus(roomStatus);
        return room;
    }

    public static Room buildRoom(Hotel hotel, RoomType roomType, int roomNumber, long bookingStartDate,
                                 long bookingEndDate, String roomStatus) {
        Room room = buildRoom(hotel, roomType, roomNumber, bookingStartDate, bookingEndDate);
        room.setStatus(roomStatus);
        return room;
    }

    public static Room buildRoom(Hotel hotel, RoomType roomType, int roomNumber, long bookingStartDate,
                                 long bookingEndDate) {
        Room room = new Room();
        room.setHotel(hotel);
        room.setRoomType(roomType);
        room.setRoomNumber(roomNumber);
        room.setBookingStartDate(bookingStartDate);
        room.setBookingEndDate(bookingEndDate);
        return room;
    }


    public static Bill buildBill(long billId, User user, int totalPersons, long checkInDate, long checkOutDate,
                                 List<Long> roomIdList, int paymentAmount, String billStatus) {
        Bill bill = buildBill(user, totalPersons, checkInDate, checkOutDate, roomIdList, paymentAmount);
        bill.setId(billId);
        bill.setStatus(billStatus);
        return bill;
    }

    public static Bill buildBill(User user, int totalPersons, long checkInDate, long checkOutDate,
                                 List<Long> roomIdList, int paymentAmount) {
        Bill bill = new Bill();
        bill.setUser(user);
        bill.setTotalPersons(totalPersons);
        bill.setCheckInDate(checkInDate);
        bill.setCheckOutDate(checkOutDate);
        bill.setRoomIdList(roomIdList);
        bill.setPaymentAmount(paymentAmount);
        return bill;
    }

    public static Bill buildBill(Bill bill, List<Room> roomList) {
        bill.setRoomList(roomList);
        return bill;
    }

    public static Bill buildNewBill(User user, int totalPersons, long checkInDate, long checkOutDate,
                                    List<Room> roomList, int paymentAmount) {
        Bill bill = new Bill();
        bill.setUser(user);
        bill.setTotalPersons(totalPersons);
        bill.setCheckInDate(checkInDate);
        bill.setCheckOutDate(checkOutDate);
        bill.setRoomList(roomList);
        bill.setPaymentAmount(paymentAmount);
        bill.setStatus(Statuses.BILL_NOT_PAID);
        return bill;
    }


}
