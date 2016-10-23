package by.kanarski.booking.utils;


import by.kanarski.booking.constants.FieldValue;
import by.kanarski.booking.entities.*;

import java.util.List;
import java.util.Set;
import java.util.TreeMap;

public class EntityBuilder {
    private EntityBuilder() {
    }

    public static User buildUser(long userId, String firstName, String lastName, String email, String login,
                                 String password, String role, String userStatus) {
        User user = buildUser(firstName, lastName, email, login, password, role);
        user.setUserId(userId);
        user.setUserStatus(userStatus);
        return user;
    }

    public static User buildUser(String firstName, String lastName, String email, String login,
                                 String password, String role, String userStatus) {
        User user = buildUser(firstName, lastName, email, login, password, role);
        user.setUserStatus(userStatus);
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
        location.setLocationId(locationId);
        location.setLocationStatus(locationStatus);
        return location;
    }

    public static Location buildLocation(String country, String city, String locationStatus) {
        Location location = buildLocation(country, city);
        location.setLocationStatus(locationStatus);
        return location;
    }

    public static Location buildLocation(String country, String city) {
        Location location = new Location();
        location.setCountry(country);
        location.setCity(city);
        return location;
    }

    public static Hotel buildHotel(long hotelId, Location hotelLocation, String hotelName, String hotelStatus) {
        Hotel hotel = new Hotel();
        hotel.setHotelId(hotelId);
        hotel.setLocation(hotelLocation);
        hotel.setHotelName(hotelName);
        hotel.setHotelStatus(hotelStatus);
        return hotel;
    }

    public static Hotel buildHotel(long hotelId, String hotelCountry, String hotelCity, String hotelName, String hotelStatus) {
        Hotel hotel = buildHotel(hotelCountry, hotelCity, hotelName);
        hotel.setHotelId(hotelId);
        hotel.setHotelStatus(hotelStatus);
        return hotel;
    }

    public static Hotel buildHotel(String hotelCountry, String hotelCity, String hotelName, String hotelStatus) {
        Hotel hotel = buildHotel(hotelCountry, hotelCity, hotelName);
        hotel.setHotelStatus(hotelStatus);
        return hotel;
    }

    public static Hotel buildHotel(String hotelCountry, String hotelCity, String hotelName) {
        Location location = buildLocation(hotelCountry, hotelCity);
        Hotel hotel = new Hotel();
        hotel.setLocation(location);
        hotel.setHotelName(hotelName);
        return hotel;
    }

    public static Hotel buildHotel(long hotelId, String hotelCountry, String hotelCity, String hotelName) {
        Location location = buildLocation(hotelCountry, hotelCity);
        Hotel hotel = new Hotel();
        hotel.setHotelId(hotelId);
        hotel.setLocation(location);
        hotel.setHotelName(hotelName);
        hotel.setHotelStatus(FieldValue.STATUS_ACTIVE);
        return hotel;
    }

    public static RoomType buildRoomType(long roomTypeId, String roomTypeName, int maxPersons, double roomPricePerNight,
                                         Set<String> facilities, String roomTypeStatus) {
        RoomType roomType = buildRoomType(roomTypeName, maxPersons, roomPricePerNight, facilities);
        roomType.setRoomTypeId(roomTypeId);
        roomType.setRoomTypeName(roomTypeName);
        roomType.setMaxPersons(maxPersons);
        roomType.setPricePerNight(roomPricePerNight);
        roomType.setFacilities(facilities);
        roomType.setRoomTypeStatus(roomTypeStatus);
        return roomType;
    }

    public static RoomType buildRoomType(String roomTypeName, int maxPersons, double roomPricePerNight,
                                         Set<String> facilities, String roomTypeStatus) {
        RoomType roomType = buildRoomType(roomTypeName, maxPersons, roomPricePerNight, facilities);
        roomType.setRoomTypeName(roomTypeName);
        roomType.setMaxPersons(maxPersons);
        roomType.setPricePerNight(roomPricePerNight);
        roomType.setFacilities(facilities);
        roomType.setRoomTypeStatus(roomTypeStatus);
        return roomType;
    }

    public static RoomType buildRoomType(String roomTypeName, int maxPersons, double roomPricePerNight,
                                         Set<String> facilities) {
        RoomType roomType = new RoomType();
        roomType.setRoomTypeName(roomTypeName);
        roomType.setMaxPersons(maxPersons);
        roomType.setPricePerNight(roomPricePerNight);
        roomType.setFacilities(facilities);
        return roomType;
    }

    public static RoomType buildRoomType(long roomTypeId, String roomTypeName, int maxPersons, double roomPricePerNight,
                                         Set<String> facilities) {
        RoomType roomType = new RoomType();
        roomType.setRoomTypeId(roomTypeId);
        roomType.setRoomTypeName(roomTypeName);
        roomType.setMaxPersons(maxPersons);
        roomType.setPricePerNight(roomPricePerNight);
        roomType.setFacilities(facilities);
        roomType.setRoomTypeStatus(FieldValue.STATUS_ACTIVE);
        return roomType;
    }

    public static Room buildRoom(long roomId, Hotel hotel, RoomType roomType, int roomNumber,
                                 TreeMap<Long, Long> bookedDates, String roomStatus) {
        Room room = buildRoom(hotel, roomType, roomNumber, bookedDates);
        room.setRoomId(roomId);
        room.setRoomStatus(roomStatus);
        return room;
    }



    public static Room buildRoom(Hotel hotel, RoomType roomType, int roomNumber, TreeMap<Long, Long> bookedDates, String roomStatus) {
        Room room = buildRoom(hotel, roomType, roomNumber, bookedDates);
        room.setRoomStatus(roomStatus);
        return room;
    }

    public static Room buildRoom(Hotel hotel, RoomType roomType, int roomNumber, TreeMap<Long, Long> bookedDates) {
        Room room = new Room();
        room.setHotel(hotel);
        room.setRoomType(roomType);
        room.setRoomNumber(roomNumber);
        room.setBookedDates(bookedDates);
        return room;
    }

    public static Bill buildBill(long billId, User user, int totalPersons, long checkInDate, long checkOutDate,
                                 List<Long> roomIdList, double paymentAmount, String billStatus) {
        Bill bill = buildBill(user, totalPersons, checkInDate, checkOutDate, roomIdList, paymentAmount);
        bill.setBillId(billId);
        bill.setBillStatus(billStatus);
        return bill;
    }

//    public static Bill buildBill(long billId, User user, int totalPersons, long checkInDate, long checkOutDate,
//                                 List<Room> roomList, double paymentAmount, String billStatus) {
//        Bill bill = buildBill(user, totalPersons, checkInDate, checkOutDate, roomList, paymentAmount);
//        bill.setBillId(billId);
//        bill.setBillStatus(billStatus);
//        return bill;
//    }

    public static Bill buildBill(User user, int totalPersons, long checkInDate, long checkOutDate,
                                 List<Long> roomIdList, double paymentAmount) {
        Bill bill = new Bill();
        bill.setClient(user);
        bill.setTotalPersons(totalPersons);
        bill.setCheckInDate(checkInDate);
        bill.setCheckOutDate(checkOutDate);
        bill.setBookedRoomIdList(roomIdList);
        bill.setPaymentAmount(paymentAmount);
        return bill;
    }

//    public static Bill buildBill(User user, int totalPersons, long checkInDate, long checkOutDate,
//                                 List<Room> roomList, double paymentAmount) {
//        Bill bill = new Bill();
//        bill.setClient(user);
//        bill.setTotalPersons(totalPersons);
//        bill.setCheckInDate(checkInDate);
//        bill.setCheckOutDate(checkOutDate);
//        bill.setBookedRoomList(roomList);
//        bill.setPaymentAmount(paymentAmount);
//        return bill;
//    }




}
